package com.data.touchstone.wallfeed.representations;

import com.fasterxml.jackson.databind.JsonNode;
import com.myntra.touch.commons.generics.JsonUtils;
import io.dropwizard.jersey.params.BooleanParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.valuehandling.UnwrapValidatedValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Author: mukthar.m@myntra.com
 * Date: 2018-01-05
 * <p>
 * Wallfeed input json validator and pojo
 */
public class WallfeedRequest {

    /**
     * The basic params required for wall-feed to understand incoming request.
     * {"event":"packet_dispatched","event_date":"2017-11-05T01:38:03+05:30","source":"OMS","storeId":"1","return_type":"COURIER_RETURN"}
     */
    @NotBlank
    @Length(min = 2, max = 255)
    private String event;

    @NotBlank
    @Pattern(regexp = "(\\d\\d\\d\\d-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])T(\\d\\d:\\d\\d:\\d\\d)\\+05:30)")
    private String event_date;

    @NotBlank
    @Length(min = 1, max = 2)
    private String storeId;

    @Length(min = 1, max = 50)
    private String return_type;

    @NotNull
    @UnwrapValidatedValue(false)
    private boolean event_date_range;

    @Pattern(regexp = "(\\d\\d\\d\\d-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])T(\\d\\d:\\d\\d))")
    private String start_date;

    @Pattern(regexp = "(\\d\\d\\d\\d-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])T(\\d\\d:\\d\\d))")
    private String end_date;

    public WallfeedRequest() {
    }

    public WallfeedRequest(String event,
                           String event_date,
                           String storeId,
                           String return_type,
                           boolean event_date_range,
                           String start_date,
                           String end_date) {
        this.event = event;
        this.event_date = event_date;
        this.storeId = storeId;
        this.return_type = return_type;
        this.event_date_range = event_date_range;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    /**
     * setter & getters
     */
    public void setstart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getstart_date() {
        return this.start_date;
    }

    public void setend_date(String end_date) {
        this.end_date = end_date;
    }

    public String getend_date() {
        return this.end_date;
    }

    public void setevent_date_range(boolean event_date_range) {
        this.event_date_range = event_date_range;
    }

    public boolean getevent_date_range() {
        return this.event_date_range;
    }

    public void setreturn_type(String return_type) {
        this.return_type = return_type;
    }

    public void setevent(String event) {
        this.event = event;
    }

    public void setevent_date(String eventDate) {
        this.event_date = eventDate;
    }

    public void setstoreId(String storeId) {
        this.storeId = storeId;
    }



    public String getevent() {
        return this.event;
    }

    public String getstoreId() {
        return this.storeId;
    }

    public String getevent_date() {
        return this.event_date;
    }

    public String getreturn_type() {
        return this.return_type;
    }


    public String toString() {
        return "[event=" + this.event + ", " +
                "event_date_range=" + this.event_date_range + ", " +
                "event_date=" + this.event_date + ", " +
                "storeId=" + this.storeId + ", " +
                "ReturnType=" + this.return_type + ", " +
                "start_date=" + this.start_date + ", " +
                "end_date=" + this.end_date +
                "]";
    }
}
