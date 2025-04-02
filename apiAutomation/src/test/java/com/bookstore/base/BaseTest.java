package com.bookstore.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.bookstore.utils.ExtentReportManager;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected static String authToken;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        if (extent == null) {
            extent = ExtentReportManager.getInstance();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed successfully");
        }
        ExtentReportManager.removeTest();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
} 