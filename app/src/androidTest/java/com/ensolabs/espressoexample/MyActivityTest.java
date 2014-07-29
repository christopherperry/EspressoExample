package com.ensolabs.espressoexample;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static com.google.android.apps.common.testing.ui.testapp.LongListMatchers.isFooter;
import static com.google.android.apps.common.testing.ui.testapp.LongListMatchers.withItemContent;
import static com.google.android.apps.common.testing.ui.testapp.LongListMatchers.withItemSize;

import android.test.ActivityInstrumentationTestCase2;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {
  public MyActivityTest() {
    super(MyActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Espresso will not launch our activity for us, we must launch it via getActivity().
    getActivity();
  }

  public void testListPopulated() {
    onData();
  }
}