package com.rest.utills;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;

@Log4j2
public class StringMan {

    @Step
    public static String getRandomString(int length) {
        String generatedRandomText = RandomStringUtils.random(length, true, true);
        log.info("Generated random string: [" + generatedRandomText + "]");
        return generatedRandomText;
    }

    @Step
    public static int getRandomNumber(int maxDigits) {
        int maxValue = (int) Math.pow(10, maxDigits) - 1;
        Random random = new Random();
        int generatedRandomNumber = random.nextInt(maxValue + 1);
        log.info("Generated random number: [" + generatedRandomNumber + "]");
        return generatedRandomNumber;
    }

    @Step
    public static String makeUniqueEmail(String email) {
        int atIndex = email.indexOf(64);
        String var10000 = email.substring(0, atIndex);
        String result = var10000 + "+" + String.valueOf((new Date()).getTime())
                .substring(8) + getRandomString(3).toLowerCase() + email.substring(atIndex);
        log.info("Generated unique email: [" + result + "]");
        return result;
    }
}
