package com.data.touchstone.wallfeed.handlers;

import com.data.touchstone.wallfeed.controller.WallfeedRestController;
import com.data.touchstone.wallfeed.meta.SCMEventName;
import com.data.touchstone.wallfeed.representations.WallfeedRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.myntra.touch.commons.generics.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Author: mukthar.m@myntra.com
 * Date: 2018-01-05
 * <p>
 * Wall-Feed request handler
 */
public class FeedRequestHandler {
    private final static Logger LOG = LoggerFactory.getLogger(WallfeedRestController.class);

    public FeedRequestHandler handle(WallfeedRequest wallfeedRequest) {

        String requestedEvent = wallfeedRequest.getevent();
        LOG.info(wallfeedRequest.toString());
        LOG.info("Now processing \"event type\": " + requestedEvent);

        if (requestedEvent
                .equalsIgnoreCase("packet_dispatched")) {

            SCMEventName.PACKET_DISPATCHED.toString().toLowerCase();

        } else if (requestedEvent
                .equalsIgnoreCase("packet_delivered")) {

        } else if (requestedEvent
                .equalsIgnoreCase("return_refund")) {

        } else if (requestedEvent
                .equalsIgnoreCase("return_rto_refund")) {

        } else if (requestedEvent
                .equalsIgnoreCase("try_and_buy_refund")) {

        } else if (requestedEvent
                .equalsIgnoreCase("customer_return")) {

        } else if (requestedEvent
                .equalsIgnoreCase("courier_return")) {

        }




        return this;
    }
}
