package com.mycompany.mypackage;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest
{
    @BeforeClass
    public static void runOnceBeforeClass() {
        // Run before all tests.
    }

    @AfterClass
    public static void doYourOneTimeTeardown() {
        // Run after all tests.
    }

    @Test
    public void test_do_something(){

        //Assert.assertTrue(MainApp.doSomething());
    }
}
