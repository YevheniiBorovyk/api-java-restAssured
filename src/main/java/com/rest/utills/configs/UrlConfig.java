package com.rest.utills.configs;

import io.restassured.path.json.JsonPath;

import java.io.File;

public class UrlConfig {

    private static final String CONFIG_FILE_PATH = "src/main/resources/config.json";

    private UrlConfig() {
    }

    public static String getUrl(String env, String service) {
        return getJsonPath(CONFIG_FILE_PATH).getString(service + "." + env);
    }

    public static JsonPath getJsonPath(String file_Path) {
        File file = new File(file_Path);
        return new JsonPath(file);
    }
}



