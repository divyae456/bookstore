package com.bookstore.tests;

import com.bookstore.base.BaseTest;
import com.bookstore.constants.APIEndpoints;
import com.bookstore.payloads.BookPayload;
import com.bookstore.utils.RestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BookTests extends BaseTest {
    private static Integer bookId;

    @Test(priority = 1, dependsOnMethods = {"com.bookstore.tests.AuthenticationTests.testUserLogin"})
    public void testAddBook() {
        BookPayload book = BookPayload.builder()
                .name("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .publishedYear(1925)
                .bookSummary("A story of decadence and excess...")
                .build();

        Response response = RestUtils.post(APIEndpoints.BOOKS, book, authToken);
        assertEquals(response.getStatusCode(), 200);
        
        BookPayload createdBook = response.as(BookPayload.class);
        bookId = createdBook.getId();
        assertNotNull(bookId);
        test.pass("Book created successfully with ID: " + bookId);
    }

    @Test(priority = 2, dependsOnMethods = "testAddBook")
    public void testGetBookById() {
        Response response = RestUtils.get(APIEndpoints.BOOKS + bookId, authToken);
        assertEquals(response.getStatusCode(), 200);
        
        BookPayload book = response.as(BookPayload.class);
        assertEquals(book.getId(), bookId);
        test.pass("Book retrieved successfully");
    }

    @Test(priority = 3, dependsOnMethods = "testAddBook")
    public void testUpdateBook() {
        BookPayload updateBook = BookPayload.builder()
                .name("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .publishedYear(1925)
                .bookSummary("Updated summary...")
                .id(bookId)
                .build();

        Response response = RestUtils.put(APIEndpoints.BOOKS + bookId, updateBook, authToken);
        assertEquals(response.getStatusCode(), 200);
        
        BookPayload updatedBook = response.as(BookPayload.class);
        assertEquals(updatedBook.getBookSummary(), "Updated summary...");
        test.pass("Book updated successfully");
    }

    @Test(priority = 4, dependsOnMethods = "testAddBook")
    public void testGetAllBooks() {
        Response response = RestUtils.get(APIEndpoints.BOOKS, authToken);
        assertEquals(response.getStatusCode(), 200);
        test.pass("All books retrieved successfully");
    }

    @Test(priority = 5, dependsOnMethods = {"testAddBook", "testUpdateBook", "testGetBookById"})
    public void testDeleteBook() {
        Response response = RestUtils.delete(APIEndpoints.BOOKS + bookId, authToken);
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Book deleted successfully"));
        test.pass("Book deleted successfully");
    }

    @Test(priority = 6)
    public void testGetNonExistentBook() {
        Response response = RestUtils.get(APIEndpoints.BOOKS + "999999", authToken);
        assertEquals(response.getStatusCode(), 404);
        test.pass("Non-existent book test passed");
    }
} 