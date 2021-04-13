package com.company.testing;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.StudentEnrolment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLogic {
    InputStream sysInBackup = System.in;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Before class");
    }

    @Before
    public void setUpBeforeMethod() throws Exception {
        System.out.println("Before each method");
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        System.out.println("After class");
    }

    @After
    public void setUpAfterMethod() throws Exception {
        System.out.println("After each method");
    }

    //tests for Models functions
    @Test
    public void testStudentEnrolmentCheckEqual() {
        System.out.println("Test check equal between an enrolment and ID data");

        Student student = new Student("S12345", "Pham Minh", Calendar.getInstance());
        Course course = new Course("COSC1234", "Info sys", 3);
        StudentEnrolment enrol = new StudentEnrolment(student, course, "2021A");
        assertTrue(enrol.checkEqual("S12345", "COSC1234", "2021A"));
        assertFalse(enrol.checkEqual("S1235", "COSC1234", "2021A"));
        assertFalse(enrol.checkEqual("asdcdsc", "casdcd", "casdcd"));
    }
}

