package com.example.bookclubapp_java;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RegisterTest {

    @Rule
    public ActivityScenarioRule<ActivityRegister> activityRule = new ActivityScenarioRule<>(ActivityRegister.class);

    @Test
    public void testingRegistrationWithValidCredentials(){
        //Inputs test credentials.
        onView(withId(R.id.userName)).perform(ViewActions.typeText("JohnDoeTest"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("TestPassword"));
        onView(withId(R.id.retypePassword)).perform(ViewActions.typeText("TestPassword"));
        //Checks if the EditTexts are visible on the screen.
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withId(R.id.retypePassword)).check(matches(isDisplayed()));

        //Closes the keyboard.
        Espresso.closeSoftKeyboard();

        //Clicks the register button.
        onView(withId(R.id.regButton)).perform(ViewActions.click());
    }

    @Test
    public void testRegistrationPasswordsNotMatching(){
        onView(withId(R.id.userName)).perform(ViewActions.typeText("JohnDoeUser1"));
        // Inputs password
        onView(withId(R.id.password)).perform(ViewActions.typeText("Password"));
        // Inputs a very different password
        onView(withId(R.id.retypePassword)).perform(ViewActions.typeText("Wordpass"));

        // Closes the keyboard
        Espresso.closeSoftKeyboard();

        // Clicks the Register button
        onView(withId(R.id.regButton)).perform(ViewActions.click());
    }

    @Test
    public void testRegistrationEmptyFields(){
        //Tests the register activity without inputs
        onView(withId(R.id.regButton)).perform(ViewActions.click());
    }
}
