package com.thedeveloperworldisyours.unitconverterpro.calculator;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by javierg on 16/09/16.
 */
public class CalculatorTest {

    Calculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new CalculatorImpl();
    }

    // can it add the positive numbers 1 and 2?
    @Test
    public void testSumPositiveNumbersOneAndTwo() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, 1.0, 2.0).equals("3.0"));
    }

    // can it add the positive numbers 2 and 2?
    @Test
    public void testSumPositiveNumbersTwoAndTwo() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, 2.0, 2.0).equals("4.0"));
    }

    // is zero neutral?
    @Test
    public void testSumZeroNeutral() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, 0, 0).equals("0.0"));
    }

    // can it add the negative numbers -1 and -2?
    @Test
    public void testSumNegativeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, -1.0, -2.0).equals("-3.0"));
    }

    // can it add a positive and a negative?
    @Test
    public void testSumPositiveAndNegative() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, -1, 1).equals("0.0"));
    }

    // how about larger numbers?
    @Test
    public void testSumLargeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.ADD, 1234, 988).equals("2222.0"));
    }

    @Test
    public void testDiminishPositiveNumbersOneAndTwo() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.SUB, 1.0, 2.0).equals("-1.0"));
    }

    @Test
    public void testDiminishPositiveNumbersTwoAndTwo() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.SUB, 2.0, 2.0).equals("0.0"));
    }

    @Test
    public void testDiminishZeroNetral(){
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.SUB, 0, 0).equals("0.0"));
    }

    @Test
    public void testDiminishNegativeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.SUB, -1, -1).equals("0.0"));
    }

    @Test
    public void testDiminishLargeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.SUB, 1234, 988).equals("246.0"));
    }

    @Test
    public void testMultiplyPositiveNumberOneAndTwo(){
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.MUL, 1, 2).equals("2.0"));
    }

    @Test
    public void testMultiplyPositiveNumberTwoAndTwo() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.MUL, 1, 1).equals("1.0"));
    }

    @Test
    public void testMultiplyZeroNeutral() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.MUL, 0,5).equals("0.0"));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.MUL, -5, -6).equals("30.0"));
    }

    @Test
    public void testMultiplyLargeNumbers() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.MUL, 201,6987).equals("1404387.0"));
    }

    @Test
    public void testDividePositiveNumberOneAndTwo(){
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.DIV, 1,2).equals("0.5"));
    }

    @Test
    public void testDivideZeroNeutral() {
        assert(mCalculator.calculateResult(CalculatorImpl.Operator.DIV, 187, 21).equals("8.904761904761905"));
    }
}
