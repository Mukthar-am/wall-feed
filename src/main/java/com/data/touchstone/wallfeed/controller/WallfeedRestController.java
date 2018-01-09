package com.data.touchstone.wallfeed.controller;

import com.data.touchstone.wallfeed.configurations.Configuration;
import com.data.touchstone.wallfeed.dao.EmployeeDB;
import com.data.touchstone.wallfeed.handlers.FeedRequestHandler;
import com.data.touchstone.wallfeed.representations.Employee;
import com.data.touchstone.wallfeed.representations.WallfeedRequest;
import com.data.touchstone.wallfeed.templates.HealthCheckTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Author: mukthar.m@myntra.com
 * Date: 2017-12-27
 */


@Slf4j
@Path("/wallfeed")
@Produces(MediaType.APPLICATION_JSON)
public class WallfeedRestController {
    final static Logger LOG = LoggerFactory.getLogger(WallfeedRestController.class);
    private Configuration configs;
    private final Validator validator;

    public WallfeedRestController(Validator validator, Configuration configs) {
        this.validator = validator;
        this.configs = configs;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response healthCheck(Configuration configs) {
        LOG.info("Wallfeed is healthier as every before to serve you on data picking.");
        LOG.info("===== + = + = + = " + this.configs.toString());
        LOG.info("===== + = + = + = " + this.configs);
        return Response.ok(HealthCheckTemplate.getHealthTemplate()).build();
    }

    @POST
    public Response requestPicker(WallfeedRequest wallfeedRequest) throws URISyntaxException {
        /** validation */
        Set<ConstraintViolation<WallfeedRequest>> violations = validator.validate(wallfeedRequest);

        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<WallfeedRequest> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }

            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();

        }
        else {
            FeedRequestHandler feedRequestHandler = new FeedRequestHandler();
            feedRequestHandler.handle(wallfeedRequest);

            return Response.ok().build();

        }
    }


    /** ====================================================================================================
     *  ToDo (Mukthar): Below are some sample retentions, to be knocked off later.
     * ====================================================================================================*/
    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Integer id) {
        Employee employee = EmployeeDB.getEmployee(id);

        if (employee != null)
            return Response.ok(employee).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/create")
    public Response createEmployee(Employee employee) throws URISyntaxException {
        /** validation */
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Employee> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }

        int newEmpId = EmployeeDB.addEmployee(employee);
        System.out.println("Created employee with new EmpID: " + newEmpId);
        LOG.info("Created employee with new EmpID: " + newEmpId);

        /**                     .created(new URI("employees/" + newEmpId)) */
        if (newEmpId != 3) {
            return Response
                    .ok(EmployeeDB.getEmployee(newEmpId))
                    .build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("/{id}")
    public Response updateEmployeeById(@PathParam("id") Integer id, Employee employee) {
        // validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Employee e = EmployeeDB.getEmployee(employee.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Employee> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            employee.setId(id);
            EmployeeDB.updateEmployee(id, employee);
            return Response.ok(employee).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeEmployeeById(@PathParam("id") Integer id) {
        Employee employee = EmployeeDB.getEmployee(id);
        if (employee != null) {
            EmployeeDB.removeEmployee(id);
            return Response.ok().build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}