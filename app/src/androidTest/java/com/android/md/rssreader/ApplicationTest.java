package com.android.md.rssreader;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.android.md.rssreader.database.ConfigUtility;

import java.io.IOException;
import java.sql.SQLException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}