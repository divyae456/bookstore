package com.bookstore.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
    private static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    private static void logResponse(Response response) {
        response.then().log().all();
    }

    public static Response post(String endpoint, Object payload) {
        Response response = getRequestSpecification()
                .body(payload)
                .post(endpoint);
        logResponse(response);
        return response;
    }

    public static Response post(String endpoint, Object payload, String token) {
        Response response = getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .post(endpoint);
        logResponse(response);
        return response;
    }

    public static Response get(String endpoint, String token) {
        Response response = getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .get(endpoint);
        logResponse(response);
        return response;
    }

    public static Response put(String endpoint, Object payload, String token) {
        Response response = getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .put(endpoint);
        logResponse(response);
        return response;
    }

    public static Response delete(String endpoint, String token) {
        Response response = getRequestSpecification()
                .header("Authorization", "Bearer " + token)
                .delete(endpoint);
        logResponse(response);
        return response;
    }
} 