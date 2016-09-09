package com.thedeveloperworldisyours.unitconverterpro.currency;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.common.utils.PreferencesManager;
import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.Rate;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements CurrencyView, TextWatcher {

    private static final String TAG = "CurrencyFragment";
    private static final int POUNDS = 8;
    private static final int DOLLAR_US = 29;
    private static final int YEN = 15;

    @BindView(R.id.fragment_currency_progressbar)
    ProgressBar mProgressBar;

    @BindView(R.id.fragment_currency_euro_input_layout)
    TextInputLayout mEuroInputLayout;

    @BindView(R.id.fragment_currency_dollar_input_layout)
    TextInputLayout mDollarInputLayout;

    @BindView(R.id.fragment_currency_pound_input_layout)
    TextInputLayout mPoundInputLayout;

    @BindView(R.id.fragment_currency_yen_input_layout)
    TextInputLayout mYenInputLayout;

    @BindView(R.id.fragment_currency_pound_edit_text)
    EditText mPoundEditText;

    @BindView(R.id.fragment_currency_dollar_edit_text)
    EditText mDollarEditText;

    @BindView(R.id.fragment_currency_euro_edit_text)
    EditText mEuroEditText;

    @BindView(R.id.fragment_currency_yen_edit_text)
    EditText mYenEditText;

    private View mView;

    private List<Rate> mListRate;

    private CurrencyPresenter mCurrencyPresenter;
    private RateDataSource mDataSource;
    private CurrencyInteractionListener mListener;

    public CurrencyFragment() {
        // Required empty public constructor
    }

    public static CurrencyFragment newInstance() {
        CurrencyFragment fragment = new CurrencyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_currency, container, false);

        mDataSource = new RateDataSource(getActivity());
        mDataSource.open();

        ButterKnife.bind(this, mView);
        mPoundEditText.addTextChangedListener(this);
        mDollarEditText.addTextChangedListener(this);
        mEuroEditText.addTextChangedListener(this);
        mYenEditText.addTextChangedListener(this);

        mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.currency_color), android.graphics.PorterDuff.Mode.MULTIPLY);
        mCurrencyPresenter = new CurrencyPresenterImpl(this, mDataSource);

        mCurrencyPresenter.callService();

        return mView;
    }

    @Override
    public void successful(Currency currency) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.fragment_currency_update));
        stringBuilder.append(" ");
        stringBuilder.append(currency.getDate());
        stringBuilder.append(" ");
        stringBuilder.append(getCurrentTime());

        PreferencesManager.getInstance().setUpdate(stringBuilder.toString());

        mListener.onFragmentInteraction(stringBuilder.toString());
        Snackbar.make(mView, stringBuilder.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    @Override
    public void error() {
        showSnackBar(getString(R.string.fragment_currency_error));
    }

    @Override
    public void generalError() {
        showSnackBar(getString(R.string.fragment_currency_general_error));
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mEuroInputLayout.setVisibility(View.GONE);
        mDollarInputLayout.setVisibility(View.GONE);
        mPoundInputLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        getData();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                mEuroInputLayout.setVisibility(View.VISIBLE);
                mDollarInputLayout.setVisibility(View.VISIBLE);
                mPoundInputLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onResume() {
        mDataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        mDataSource.close();
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CurrencyInteractionListener) {
            //init the listener
            mListener = (CurrencyInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CurrencyInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        double currentValue = 1.0;
        double userValue;
        double coinReference;
        double euroValue;

        if (mListRate.size() != 0) {
            if (mEuroEditText.getText().hashCode() == s.hashCode()) {
                if (mEuroEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {

                    userValue = Double.valueOf(mEuroEditText.getText().toString());
                    coinReference = 1.0;
                    setPounds(userValue, coinReference);

                    setUSDollar(userValue, coinReference);

                    setYen(userValue, coinReference);
                }

            } else if (mPoundEditText.getText().hashCode() == s.hashCode()) {
                if (mPoundEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {

                    userValue = Double.valueOf(mPoundEditText.getText().toString());
                    coinReference = mListRate.get(POUNDS).getValue();
                    setEuro(currentValue, userValue, coinReference);

                    euroValue = calculateDouble(currentValue, userValue, coinReference);

                    userValue = euroValue;
                    coinReference = 1.0;
                    setUSDollar(userValue, coinReference);
                    setYen(userValue, coinReference);
                }

            } else if (mDollarEditText.getText().hashCode() == s.hashCode()) {
                if (mDollarEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {

                    userValue = Double.valueOf(mDollarEditText.getText().toString());
                    coinReference = mListRate.get(DOLLAR_US).getValue();
                    setEuro(currentValue, userValue, coinReference);

                    euroValue = calculateDouble(currentValue, userValue, coinReference);

                    userValue = euroValue;
                    coinReference = 1.0;
                    setPounds(userValue, coinReference);
                    setYen(userValue, coinReference);
                }

            } else if (mYenEditText.getText().hashCode() == s.hashCode()) {
                if (mYenEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {

                    userValue = Double.valueOf(mYenEditText.getText().toString());
                    coinReference = mListRate.get(YEN).getValue();
                    setEuro(currentValue, userValue, coinReference);

                    euroValue = calculateDouble(currentValue, userValue, coinReference);

                    userValue = euroValue;
                    coinReference = 1.0;
                    setUSDollar(userValue, coinReference);
                    setPounds(userValue, coinReference);
                }
            }
        } else {
            showSnackBar(getString(R.string.fragment_currency_general_error));
        }
    }


    public void getData() {
        mListRate = mDataSource.getAllRates();
        Log.d(TAG, String.valueOf(mListRate.size()));
    }

    public void setPounds(double userValue, double coinReference) {
        mPoundEditText.removeTextChangedListener(this);
        mPoundEditText.setText(calculate(mListRate.get(POUNDS).getValue(), userValue, coinReference));
        mPoundEditText.addTextChangedListener(this);
    }

    public void setEuro(double currentValue, double userValue, double coinReference) {
        mEuroEditText.removeTextChangedListener(this);
        mEuroEditText.setText(calculate(currentValue, userValue, coinReference));
        mEuroEditText.addTextChangedListener(this);
    }

    public void setUSDollar(double userValue, double coinReference) {
        mDollarEditText.removeTextChangedListener(this);
        mDollarEditText.setText(calculate(mListRate.get(DOLLAR_US).getValue(), userValue, coinReference));
        mDollarEditText.addTextChangedListener(this);
    }

    public void setYen(double userValue, double coinReference) {
        mYenEditText.removeTextChangedListener(this);
        mYenEditText.setText(calculate(mListRate.get(YEN).getValue(), userValue, coinReference));
        mYenEditText.addTextChangedListener(this);
    }

    public String calculate(double currentValue, double userValue, double coinReference) {

        return String.valueOf(round((currentValue * userValue) / coinReference, 1));
    }

    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10000, scale)) / Math.pow(10000, scale);
    }

    public double calculateDouble(double currentValue, double userValue, double coinReference) {
        return (currentValue * userValue) / coinReference;
    }

    public void showSnackBar(String string) {
        hideKeyboard();
        Snackbar.make(mView, string, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setEmptyField() {

        mPoundEditText.removeTextChangedListener(this);
        mPoundEditText.setText("");
        mPoundEditText.addTextChangedListener(this);

        mEuroEditText.removeTextChangedListener(this);
        mEuroEditText.setText("");
        mEuroEditText.addTextChangedListener(this);

        mDollarEditText.removeTextChangedListener(this);
        mDollarEditText.setText("");
        mDollarEditText.addTextChangedListener(this);

        mYenEditText.removeTextChangedListener(this);
        mYenEditText.setText("");
        mYenEditText.addTextChangedListener(this);
    }
}
