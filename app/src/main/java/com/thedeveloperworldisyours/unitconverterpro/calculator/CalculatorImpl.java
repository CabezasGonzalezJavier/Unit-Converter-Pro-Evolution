package com.thedeveloperworldisyours.unitconverterpro.calculator;

/**
 * Created by javierg on 16/09/16.
 */
public class CalculatorImpl implements Calculator{

    public enum Operator {ADD, SUB, DIV, MUL}


    public String calculateResult(Operator operator, double mFirstValue, double mSecondValue) {
        switch (operator) {
            case DIV:
                return String.valueOf(div(mFirstValue, mSecondValue));
            case ADD:
                return String.valueOf(add(mFirstValue, mSecondValue));
            case MUL:
                return String.valueOf(mul(mFirstValue, mSecondValue));
            case SUB:
                return String.valueOf(sub(mFirstValue, mSecondValue));
            default:
                return String.valueOf("");
        }
    }

    /**
     * Addition operation
     */
    public double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    /**
     * Substract operation
     */
    public double sub(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    /**
     * Divide operation
     */
    public double div(double firstOperand, double secondOperand) {
//        checkArgument(secondOperand != 0, "secondOperand must be != 0, you cannot divide by zero");
        return firstOperand / secondOperand;
    }

    /**
     * Multiply operation
     */
    public double mul(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }
}
