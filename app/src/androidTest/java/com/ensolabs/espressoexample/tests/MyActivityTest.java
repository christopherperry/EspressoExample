package com.ensolabs.espressoexample.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.ensolabs.espressoexample.MyActivity;
import com.ensolabs.espressoexample.R;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.ensolabs.espressoexample.MyActivity.Person;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


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

  public void testListClick() {
    onData(anything())
      .inAdapterView(withId(R.id.list))
      .atPosition(40)
      .perform(click());

    // The above launches a new Activity that shows "lastName, firstName"
    onView(withId(R.id.fullName)).check(matches(withText("40, Arthur")));
  }

  public void testListClickWithText() {
    onData(allOf(is(instanceOf(Person.class)), is(person("Arthur",  "25"))))
      .perform(click());

    // The above launches a new Activity that shows "lastName, firstName"
    onView(withId(R.id.fullName)).check(matches(withText("25, Arthur")));
  }

  static Matcher<Person> person(final String first, final String last) {
    return new BaseMatcher<Person>() {

      @Override public boolean matches(Object o) {
        if (o instanceof Person) {
          Person that = (Person) o;
          return first.equals(that.getFirstName()) && last.equals(that.getLastName());
        }
        return false;
      }

      @Override public void describeTo(Description description) {
        description.appendText("has name: " + last + ", " + first);
      }
    };
  }

}