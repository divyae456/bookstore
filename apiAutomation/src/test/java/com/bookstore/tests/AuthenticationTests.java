package com.bookstore.tests;

import com.bookstore.base.BaseTest;
import com.bookstore.constants.APIEndpoints;
import com.bookstore.payloads.UserPayload;
import com.bookstore.utils.RestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AuthenticationTests extends BaseTest {

    @Test(priority = 1)
    public void testUserSignup() {
        UserPayload user = UserPayload.builder()
                .email("test" + System.currentTimeMillis() + "@example.com")
                .password("Test@123")
                .build();

        Response response = RestUtils.post(APIEndpoints.SIGNUP, user);
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("User created successfully"));
    }

    @Test(priority = 2)
    public void testUserLogin() {
        UserPayload user = UserPayload.builder()
                .email("divyae456@gmail.com")
                .password("bookStore@2025")
                .build();

        Response response = RestUtils.post(APIEndpoints.LOGIN, user);
        assertEquals(response.getStatusCode(), 200);
        
        authToken = response.jsonPath().getString("access_token");
        assertNotNull(authToken);
    }

    @Test(priority = 3)
    public void testInvalidLogin() {
        UserPayload user = UserPayload.builder()
                .email("invalid@example.com")
                .password("wrongpassword")
                .build();

        Response response = RestUtils.post(APIEndpoints.LOGIN, user);
        assertEquals(response.getStatusCode(), 400);
    }
} 