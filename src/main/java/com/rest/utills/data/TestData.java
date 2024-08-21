package com.rest.utills.data;

import java.util.Date;

public class TestData {

    public static Long getTimeStamp() {
        Date currentDate = new Date();
        return currentDate.getTime() / 1000;
    }
}
