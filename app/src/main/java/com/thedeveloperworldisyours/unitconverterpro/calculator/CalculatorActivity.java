package com.thedeveloperworldisyours.unitconverterpro.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.thedeveloperworldisyours.unitconverterpro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.activity_calculator_result)
    TextView mResult;
    @BindView(R.id.activity_calculator_dot)
    Button mDotButton;

    private double mFirstValue = 0;
    private double mSecondValue = 0;

    private boolean mAfterOperatorClean = false;
    private boolean mClean = true;

    private CalculatorImpl.Operator mCase;

    private final static String RESULT = "result";
    private final static String FIRST_VALUE = "first";
    private final static String SECOND_VALUE = "second";

    Calculator mCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        mResult.setText("0");
        mResult.setMovementMethod(new ScrollingMovementMethod());
        mCalculator = new CalculatorImpl();
        restoreMe(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(RESULT, mResult.getText().toString());
        outState.putDouble(FIRST_VALUE, mFirstValue);
        outState.putDouble(SECOND_VALUE, mSecondValue);
    }

    private void restoreMe(Bundle state) {
        if (state != null) {

            mResult.setText(state.getString(RESULT));
            mFirstValue = state.getDouble(FIRST_VALUE);
            mSecondValue = state.getDouble(SECOND_VALUE);

        }
    }

    @OnClick(R.id.activity_calculator_remove)
    public void removeElements(View view) {
        String result = mResult.getText().toString();
        if (result.length() != 1) {
            if (result.substring(result.length() - 1, result.length()).equals(".")) {
                mDotButton.setEnabled(true);
            }
            mResult.setText(result.substring(0, result.length() - 1));
        } else {
            mResult.setText("0");
        }
        mResult.setMovementMethod(ArrowKeyMovementMethod.getInstance());
    }


    @OnClick(R.id.activity_calculator_equals)
    public void showResult(View view) {

        if (mResult.getText().equals("")) {
            mSecondValue = 0;
        } else {
            mSecondValue = Double.valueOf(mResult.getText().toString());
        }
        String result = mCalculator.calculateResult(mCase, mFirstValue, mSecondValue);
        mResult.setText(result);
        if (result.equals("")) {
            mFirstValue = 0;
        } else {
            mFirstValue = Double.valueOf(result);
        }
        checkDot(result);
        mSecondValue = 0;
        mClean = true;
    }

    public void checkDot(String result) {
        if (result.contains(".")) {
            mDotButton.setEnabled(false);
        } else {
            mDotButton.setEnabled(true);
        }
    }

    @OnClick(R.id.activity_calculator_divide)
    public void divide(View view) {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = CalculatorImpl.Operator.DIV;
        mAfterOperatorClean = true;
        mDotButton.setEnabled(true);
    }

    @OnClick(R.id.activity_calculator_add)
    public void add(View view) {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = CalculatorImpl.Operator.ADD;
        mAfterOperatorClean = true;
        mDotButton.setEnabled(true);
    }

    @OnClick(R.id.activity_calculator_diminish)
    public void diminish() {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = CalculatorImpl.Operator.SUB;
        mAfterOperatorClean = true;
        mDotButton.setEnabled(true);
    }

    @OnClick(R.id.activity_calculator_multiply)
    public void multiply() {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = CalculatorImpl.Operator.MUL;
        mAfterOperatorClean = true;
        mDotButton.setEnabled(true);
    }

    @OnClick(R.id.activity_calculator_one)
    public void oneClick(View view) {
        cleanZero();
        cleanScreen();
        addString("1");
    }

    @OnClick(R.id.activity_calculator_two)
    public void twoClick(View view) {
        cleanZero();
        cleanScreen();
        addString("2");
    }

    @OnClick(R.id.activity_calculator_three)
    public void threeClick(View view) {
        cleanZero();
        cleanScreen();
        addString("3");
    }

    @OnClick(R.id.activity_calculator_four)
    public void fourClick(View view) {
        cleanZero();
        cleanScreen();
        addString("4");
    }

    @OnClick(R.id.activity_calculator_five)
    public void fiveClick(View view) {
        cleanZero();
        cleanScreen();
        addString("5");
    }

    @OnClick(R.id.activity_calculator_six)
    public void sixClick(View view) {
        cleanZero();
        cleanScreen();
        addString("6");
    }

    @OnClick(R.id.activity_calculator_seven)
    public void sevenClick(View view) {
        cleanZero();
        cleanScreen();
        addString("7");
    }

    @OnClick(R.id.activity_calculator_eight)
    public void eightClick(View view) {
        cleanZero();
        cleanScreen();
        addString("8");
    }

    @OnClick(R.id.activity_calculator_nine)
    public void nineClick(View view) {
        cleanZero();
        cleanScreen();
        addString("9");
    }

    @OnClick(R.id.activity_calculator_zero)
    public void zeroClick(View view) {
        if (mAfterOperatorClean) {
            afterOperation();
        } else {
            checkDotForCleaningScreen();
        }
        addString("0");
    }

    @OnClick(R.id.activity_calculator_dot)
    public void dotClick() {
        if (mAfterOperatorClean) {
            afterOperation();
            addString("0");
        }
        addString(".");
        mDotButton.setEnabled(false);
    }

    public void addString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mResult.getText().toString());
        stringBuilder.append(string);
        mResult.setText(stringBuilder.toString());

    }

    public void checkDotForCleaningScreen() {
        if (!mResult.getText().toString().contains(".")) {
            mResult.setText("");
        }
    }

    public void cleanZero() {
        if (mResult.getText().toString().equals("0") && mResult.getText().length() == 1) {
            mClean = true;
        } else if (mResult.getText().toString().contains(".")) {
            mClean = false;
        }
    }


    public void cleanScreen() {
        if (mAfterOperatorClean) {
            afterOperation();
        } else if (mClean) {
            mResult.setText("");
            mClean = false;
        }
    }

    public void afterOperation() {
        mResult.setText("");
        mClean = false;
        mAfterOperatorClean = false;
    }

}
