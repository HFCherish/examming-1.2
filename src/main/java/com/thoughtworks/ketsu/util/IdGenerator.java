package com.thoughtworks.ketsu.util;

import java.util.UUID;

/**
 * Created by pzzheng on 5/22/17.
 */
public class IdGenerator {
    public static long next() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}
