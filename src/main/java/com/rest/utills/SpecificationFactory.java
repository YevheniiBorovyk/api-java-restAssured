package com.rest.utills;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.rest.utills.configs.UrlConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecificationFactory {

    public static final String ENV = System.getProperty("testEnv", "prod");

    private SpecificationFactory() {
    }

    public static RequestSpecification getDefaultRequestSpecification(String serviceName) {
        return new RequestSpecBuilder().setBaseUri(UrlConfig.getUrl(ENV, serviceName))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(CurlRestAssuredConfigFactory.createConfig())
                .build()
                .filter(new AllureRestAssured());
    }
}
