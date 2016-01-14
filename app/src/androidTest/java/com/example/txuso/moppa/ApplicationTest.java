package com.example.txuso.moppa;

import android.app.Application;
import android.test.ApplicationTestCase;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private MainActivity activity;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = new MainActivity();

        Factorial();

    }

    public void Factorial() {
        String actual = activity.calculateResult("4");
        String expected = "6";

        assertEquals(expected, actual);

    }




}