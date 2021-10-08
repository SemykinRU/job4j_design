package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Roman";
        int age = 32;
        long l = 99999;
        char code = '$';
        double dPoint = 6.5d;
        float fPoint = 3.3f;
        byte b = 8;
        short sh = 20;
        boolean isTrue = false;

        LOG.debug("User info name : {}, age : {}, l : {}, code : {}, dPoint : {}, fPoint : {}, b : {}, sh : {}, isTure : {}",
                name, age, l, code, dPoint, fPoint, b, sh, isTrue);
    }
}