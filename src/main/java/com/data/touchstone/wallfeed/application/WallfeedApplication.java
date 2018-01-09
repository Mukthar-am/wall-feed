package com.data.touchstone.wallfeed.application;

import com.data.touchstone.wallfeed.configurations.Configuration;
import com.data.touchstone.wallfeed.controller.WallfeedRestController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wall-feed application initializer
 */
public class WallfeedApplication extends Application<Configuration> {
    private static final Logger LOG = LoggerFactory.getLogger(WallfeedApplication.class);

    public static void main(String[] args) throws Exception {
        new WallfeedApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration conf, Environment environment) {
        LOG.info("=== Starting up wallfeed service endpoint ===");
        environment.jersey().register(new WallfeedRestController(environment.getValidator(), conf   ));
    }
}

