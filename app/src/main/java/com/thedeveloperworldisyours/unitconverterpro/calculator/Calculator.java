package com.thedeveloperworldisyours.unitconverterpro.calculator;

/**
 * Created by javierg on 16/09/16.
 */
public interface Calculator {
    public String calculateResult(CalculatorImpl.Operator operator, double mFirstValue, double mSecondValue);
    public double add(double firstOperand, double secondOperand);
    public double sub(double firstOperand, double secondOperand);
    public double div(double firstOperand, double secondOperand);
    public double mul(double firstOperand, double secondOperand);

}
