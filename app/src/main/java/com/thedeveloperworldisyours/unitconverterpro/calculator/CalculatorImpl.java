package com.thedeveloperworldisyours.unitconverterpro.calculator;

/**
 * Created by javierg on 16/09/16.
 */
public class CalculatorImpl implements Calculator{

    private static final int ADDITION = 1;
    private final static int DIMINISH = 2;
    private final static int DIVIDE = 3;
    private final static int MULTIPLY = 4;

    public String calculateResult(int operator, double mFirstValue, double mSecondValue) {
        switch (operator) {
            case DIVIDE:
                return String.valueOf(mFirstValue / mSecondValue);
            case ADDITION:
                return String.valueOf(mFirstValue + mSecondValue);
            case MULTIPLY:
                return String.valueOf(mFirstValue * mSecondValue);
            case DIMINISH:
                return String.valueOf(mFirstValue - mSecondValue);
            default:
                return String.valueOf("");
        }
    }
}
