package com.thedeveloperworldisyours.unitconverterpro.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.thedeveloperworldisyours.unitconverterpro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.activity_calculator_result)
    TextView mResult;

    private double mFirstValue = 0;
    private double mSecondValue = 0;

    private boolean mClean = true;

    private int mCase = 0;

    private static final int PLUS = 1;
    private final static int DIMINISH = 2;
    private final static int DIVIDE = 3;
    private final static int MULTIPLY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        mResult.setText("0");
    }

    @OnClick(R.id.activity_calculator_remove)
    public void removeElements(View view) {
        String result = mResult.getText().toString();
        if (result.length()!=1) {
            mResult.setText(result.substring(0, result.length() - 1));
        } else {
            mResult.setText("0");
        }
    }

    @OnClick(R.id.activity_calculator_equals)
    public void showResult(View view) {

        mSecondValue = Double.valueOf(mResult.getText().toString());
        String result = calculateResult();
        mResult.setText(result);
        mFirstValue = Double.valueOf(result);
        mSecondValue = 0;
        mClean = true;
    }

    public String calculateResult() {
        switch (mCase) {
            case DIVIDE:
                return String.valueOf(mFirstValue / mSecondValue);
            case PLUS:
                return String.valueOf(mFirstValue + mSecondValue);
            case MULTIPLY:
                return String.valueOf(mFirstValue * mSecondValue);
            case DIMINISH:
                return String.valueOf(mFirstValue - mSecondValue);
            default:
                return String.valueOf("");
        }
    }

    @OnClick(R.id.activity_calculator_divide)
    public void divide(View view) {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = DIVIDE;
        mClean = true;
    }

    @OnClick(R.id.activity_calculator_plus)
    public void plus(View view) {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = PLUS;
        mClean = true;
    }

    @OnClick(R.id.activity_calculator_diminish)
    public void diminish() {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = DIMINISH;
        mClean = true;
    }

    @OnClick(R.id.activity_calculator_multiply)
    public void multiply() {
        mFirstValue = Double.valueOf(mResult.getText().toString());
        mCase = MULTIPLY ;
        mClean = true;
    }

    @OnClick(R.id.activity_calculator_one)
    public void oneClick(View view) {
        cleanScreen();
        addString("1");
    }

    @OnClick(R.id.activity_calculator_two)
    public void twoClick(View view) {
        cleanScreen();
        addString("2");
    }

    @OnClick(R.id.activity_calculator_three)
    public void threeClick(View view) {
        cleanScreen();
        addString("3");
    }

    @OnClick(R.id.activity_calculator_four)
    public void fourClick(View view) {
        cleanScreen();
        addString("4");
    }

    @OnClick(R.id.activity_calculator_five)
    public void fiveClick(View view) {
        cleanScreen();
        addString("5");
    }

    @OnClick(R.id.activity_calculator_six)
    public void sixClick(View view) {
        cleanScreen();
        addString("6");
    }

    @OnClick(R.id.activity_calculator_seven)
    public void sevenClick(View view) {
        cleanScreen();
        addString("7");
    }

    @OnClick(R.id.activity_calculator_eight)
    public void eightClick(View view) {
        cleanScreen();
        addString("8");
    }

    @OnClick(R.id.activity_calculator_nine)
    public void nineClick(View view) {
        cleanScreen();
        addString("9");
    }

    @OnClick(R.id.activity_calculator_zero)
    public void zeroClick(View view) {
        cleanScreen();
        addString("0");
    }

    @OnClick(R.id.activity_calculator_dot)
    public void dotClick() {
        cleanScreen();
        addString(".");
    }

    public void addString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mResult.getText().toString());
        stringBuilder.append(string);
        mResult.setText(stringBuilder.toString());

    }

    public void cleanScreen() {
        if (mClean) {
            mResult.setText("");
            mClean = false;
        }
    }

}
