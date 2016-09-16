package com.thedeveloperworldisyours.unitconverterpro.calculator;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by javierg on 16/09/16.
 */
public class CalculatorTest {

    Calculator mCalculator;
    int mAddition;
    int mDiminish;
    int mDivide;
    int mMultiply;

    @Before
    public void setUp() {
        mCalculator = new CalculatorImpl();
        mAddition = 1;
        mDiminish = 2;
        mDivide = 3;
        mMultiply = 4;
    }

    // can it add the positive numbers 1 and 2?
    @Test
    public void testSumPositiveNumbersOneAndTwo() {
        assert(mCalculator.calculateResult(mAddition, 1.0, 2.0).equals("3.0"));
    }

    // can it add the positive numbers 2 and 2?
    @Test
    public void testSumPositiveNumbersTwoAndTwo() {
        assert(mCalculator.calculateResult(mAddition, 2.0, 2.0).equals("4.0"));
    }

    // is zero neutral?
    @Test
    public void testSumZeroNeutral() {
        assert(mCalculator.calculateResult(mAddition, 0, 0).equals("0.0"));
    }

    // can it add the negative numbers -1 and -2?
    @Test
    public void testSumNegativeNumbers() {
        assert(mCalculator.calculateResult(mAddition, -1.0, -2.0).equals("-3.0"));
    }

    // can it add a positive and a negative?
    @Test
    public void testSumPositiveAndNegative() {
        assert(mCalculator.calculateResult(mAddition, -1, 1).equals("0.0"));
    }

    // how about larger numbers?
    @Test
    public void testSumLargeNumbers() {
        assert(mCalculator.calculateResult(mAddition, 1234, 988).equals("2222.0"));
    }

    @Test
    public void testDiminishPositiveNumbersOneAndTwo() {
        assert(mCalculator.calculateResult(mDiminish, 1.0, 2.0).equals("-1.0"));
    }

    @Test
    public void testDiminishPositiveNumbersTwoAndTwo() {
        assert(mCalculator.calculateResult(mDiminish, 2.0, 2.0).equals("0.0"));
    }

    @Test
    public void testDiminishZeroNetral(){
        assert(mCalculator.calculateResult(mDiminish, 0, 0).equals("0.0"));
    }

    @Test
    public void testDiminishNegativeNumbers() {
        assert(mCalculator.calculateResult(mDiminish, -1, -1).equals("0.0"));
    }

    @Test
    public void testDiminishLargeNumbers() {
        assert(mCalculator.calculateResult(mDiminish, 1234, 988).equals("246.0"));
    }

    @Test
    public void testMultiplyPositiveNumberOneAndTwo(){
        assert(mCalculator.calculateResult(mMultiply, 1, 2).equals("2.0"));
    }

    @Test
    public void testMultiplyPositiveNumberTwoAndTwo() {
        assert(mCalculator.calculateResult(mMultiply, 1, 1).equals("1.0"));
    }

    @Test
    public void testMultiplyZeroNeutral() {
        assert(mCalculator.calculateResult(mMultiply, 0,5).equals("0.0"));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        assert(mCalculator.calculateResult(mMultiply, -5, -6).equals("30.0"));
    }

    @Test
    public void testMultiplyLargeNumbers() {
        assert(mCalculator.calculateResult(mMultiply, 201,6987).equals("1404387.0"));
    }

    @Test
    public void testDividePositiveNumberOneAndTwo(){
        assert(mCalculator.calculateResult(mDivide, 1,2).equals("0.5"));
    }

    @Test
    public void testDivideZeroNeutral() {
        assert(mCalculator.calculateResult(mDivide, 187, 21).equals("8.904761904761905"));
    }
}
