package com.thedeveloperworldisyours.unitconverterpro.fragment;

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
import android.widget.EditText;
import android.widget.ProgressBar;

import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.model.Rates;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenter;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenterImpl;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.Rate;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.currency.RateDataSource;
import com.thedeveloperworldisyours.unitconverterpro.view.CurrencyView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements CurrencyView, TextWatcher {

    public static final String TAG = "CurrencyFragment";
    public static final int POUNDS = 8;

    @BindView(R.id.fragment_currency_progressbar)
    ProgressBar mProgressBar;

    @BindView(R.id.fragment_currency_euro_input_layout)
    TextInputLayout mEuroInputLayout;

    @BindView(R.id.fragment_currency_dollar_input_layout)
    TextInputLayout mDollarInputLayout;

    @BindView(R.id.fragment_currency_pound_input_layout)
    TextInputLayout mPoundInputLayout;

    @BindView(R.id.fragment_currency_pound_edit_text)
    EditText mPoundEditText;

    @BindView(R.id.fragment_currency_dollar_edit_text)
    EditText mDollarEditText;

    @BindView(R.id.fragment_currency_euro_edit_text)
    EditText mEuroEditText;

    private View mView;

    private List<Rate> mListRate;

    private CurrencyPresenter mCurrencyPresenter;
    private RateDataSource mDataSource;

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

        mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.currency_color), android.graphics.PorterDuff.Mode.MULTIPLY);
        mCurrencyPresenter = new CurrencyPresenterImpl(this, mDataSource);
        mCurrencyPresenter.callService();

        return mView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void successful(Currency currency) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.fragment_currency_update));
        stringBuilder.append(" ");
        stringBuilder.append(currency.getDate());
        stringBuilder.append(" ");
        stringBuilder.append(getCurrentTime());

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
        Snackbar.make(mView, getString(R.string.fragment_currency_error), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void generalError() {
        Snackbar.make(mView, getString(R.string.fragment_currency_general_error), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
        mProgressBar.setVisibility(View.GONE);
        mEuroInputLayout.setVisibility(View.VISIBLE);
        mDollarInputLayout.setVisibility(View.VISIBLE);
        mPoundInputLayout.setVisibility(View.VISIBLE);
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if ( !mEuroEditText.getText().toString().isEmpty() && mEuroEditText.getText().hashCode() == s.hashCode()) {

            double currentValue = mListRate.get(POUNDS).getValue();
            double userValue = Double.valueOf(mEuroEditText.getText().toString());
            double coinReference = 1.0;

            mPoundEditText.removeTextChangedListener(this);
            mPoundEditText.setText(calculate(currentValue, userValue, coinReference));
            mPoundEditText.addTextChangedListener(this);

        } else if ( !mPoundEditText.getText().toString().isEmpty() && mPoundEditText.getText().hashCode() == s.hashCode()) {

            double currentValue = 1.0;
            double userValue = Double.valueOf(mPoundEditText.getText().toString());
            double coinReference = mListRate.get(POUNDS).getValue();

            mEuroEditText.removeTextChangedListener(this);
            mEuroEditText.setText(calculate(currentValue, userValue, coinReference));
            mEuroEditText.addTextChangedListener(this);
        }
    }

    public void getData() {
        mListRate = mDataSource.getAllRates();
        Log.d(TAG, String.valueOf(mListRate.size()));
    }

    public String calculate(double currentValue, double userValue, double coinReference) {
        return String.valueOf((currentValue * userValue) / coinReference);
    }

}
