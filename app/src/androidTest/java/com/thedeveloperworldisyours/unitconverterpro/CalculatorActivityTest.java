package com.thedeveloperworldisyours.unitconverterpro;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.thedeveloperworldisyours.unitconverterpro.calculator.CalculatorActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by javierg on 14/09/16.
 */
public class CalculatorActivityTest {
    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void testMultiply() {
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_multiply)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testDiminish() {
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_diminish)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("-1.0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testAddition() {
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("5.0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testDivide() {
        onView(withId(R.id.activity_calculator_eight)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("4.0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testOnClickTwiceOperator() {
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0")));
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testOnClickPlusOperatorDivideOperatorAndInsertNumber() {
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0")));
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0")));
        SystemClock.sleep(1500);
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("0.0")));
        SystemClock.sleep(1500);
    }

    @Test
    public void testOnClickRemove() {
        onView(withId(R.id.activity_calculator_six)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_seven)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_remove)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6")));

        SystemClock.sleep(1500);
    }

    @Test
    public void testConcatenateResult() {
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_multiply)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
//        Diminish
        onView(withId(R.id.activity_calculator_diminish)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3.0")));
//        Addition
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
//        Divide
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3.0")));
    }

    @Test
    public void testAllOperationTogether() {
//      Multiply
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_multiply)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
//      Diminish
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_diminish)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("-1.0")));
//      Addition
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("5.0")));
//      Divide
        onView(withId(R.id.activity_calculator_eight)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("4.0")));
    }


    @Test
    public void testAllOperationTogetherWithRotation() {
//      Multiply
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_multiply)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
        rotateScreen();
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("6.0")));
//      Diminish
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_diminish)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("-1.0")));
        rotateScreen();
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("-1.0")));
//      Addition
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_plus)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_three)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("3")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("5.0")));
        rotateScreen();
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("5.0")));
//      Divide
        onView(withId(R.id.activity_calculator_eight)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        onView(withId(R.id.activity_calculator_divide)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("8")));
        onView(withId(R.id.activity_calculator_two)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("2")));
        onView(withId(R.id.activity_calculator_equals)).perform(ViewActions.click());
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("4.0")));
        rotateScreen();
        onView(withId(R.id.activity_calculator_result)).check(matches(withText("4.0")));
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = mActivityRule.getActivity();
        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
