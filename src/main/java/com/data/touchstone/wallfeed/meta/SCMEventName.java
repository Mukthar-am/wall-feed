package com.data.touchstone.wallfeed.meta;

/**
 * Author: mukthar.m@myntra.com
 * Date: 2018-01-05
 */
public enum SCMEventName {
    PACKET_DISPATCHED("PACKET_DISPATCHED"),
    PACKET_DELIVERED("PACKET_DELIVERED");

    private final String name;

    private SCMEventName(String s) {
        name = s;
    }

    public static String valueByName(SCMEventName input) {
        String val = null;
        for (SCMEventName str : values()) {
            if (str.equals(input)) {
                val = input.name;
                break;
            }
        }
        return val;
    }
}
