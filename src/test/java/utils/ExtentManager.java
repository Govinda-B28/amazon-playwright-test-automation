package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                new ExtentSparkReporter("reports/AutomationReport.html");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}