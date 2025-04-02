package com.bookstore.constants;

import com.bookstore.utils.ConfigReader;

public class APIEndpoints {
    public static final String BASE_URL = ConfigReader.getProperty("base.url");
    public static final String SIGNUP = BASE_URL + "/signup";
    public static final String LOGIN = BASE_URL + "/login";
    public static final String BOOKS = BASE_URL + "/books/";
} 