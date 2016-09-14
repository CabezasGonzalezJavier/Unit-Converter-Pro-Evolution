package com.thedeveloperworldisyours.unitconverterpro.currency;

import android.content.Context;
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
    private static final int BULGARIAN = 1;
    private static final int CZECH = 6;
    private static final int DANISH = 7;
    private static final int HUNGARIAN =11;
    private static final int POLISH =22;

    private double mCurrentValue = 1.0;
    private double mUserValue;
    private double mCoinReference;
    private double mEuroValue;

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

    @BindView(R.id.fragment_currency_bulgarian_input_layout)
    TextInputLayout mBulgarianInputLayout;

    @BindView(R.id.fragment_currency_czech_input_layout)
    TextInputLayout mCzechInputLayout;

    @BindView(R.id.fragment_currency_danish_input_layout)
    TextInputLayout mDanishInputLayout;

    @BindView(R.id.fragment_currency_hungarian_input_layout)
    TextInputLayout mHungarianInputLayout;

    @BindView(R.id.fragment_currency_polish_input_layout)
    TextInputLayout mPolishInputLayout;

    @BindView(R.id.fragment_currency_pound_edit_text)
    EditText mPoundEditText;

    @BindView(R.id.fragment_currency_dollar_edit_text)
    EditText mDollarEditText;

    @BindView(R.id.fragment_currency_euro_edit_text)
    EditText mEuroEditText;

    @BindView(R.id.fragment_currency_yen_edit_text)
    EditText mYenEditText;

    @BindView(R.id.fragment_currency_bulgarian_edit_text)
    EditText mBulgarianEditText;

    @BindView(R.id.fragment_currency_czech_edit_text)
    EditText mCzechEditText;

    @BindView(R.id.fragment_currency_danish_edit_text)
    EditText mDanishEditText;

    @BindView(R.id.fragment_currency_hungarian_edit_text)
    EditText mHungarianEditText;

    @BindView(R.id.fragment_currency_polish_edit_text)
    EditText mPolishEditText;

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
        mBulgarianEditText.addTextChangedListener(this);
        mCzechEditText.addTextChangedListener(this);
        mDanishEditText.addTextChangedListener(this);
        mHungarianEditText.addTextChangedListener(this);
        mPolishEditText.addTextChangedListener(this);

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
        mYenInputLayout.setVisibility(View.GONE);
        mBulgarianInputLayout.setVisibility(View.GONE);
        mCzechInputLayout.setVisibility(View.GONE);
        mDanishInputLayout.setVisibility(View.GONE);
        mHungarianInputLayout.setVisibility(View.GONE);
        mPolishInputLayout.setVisibility(View.GONE);
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
                mYenInputLayout.setVisibility(View.VISIBLE);
                mBulgarianInputLayout.setVisibility(View.VISIBLE);
                mCzechInputLayout.setVisibility(View.VISIBLE);
                mDanishInputLayout.setVisibility(View.VISIBLE);
                mHungarianInputLayout.setVisibility(View.VISIBLE);
                mPolishInputLayout.setVisibility(View.VISIBLE);
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

    public void generalSetEuro(String editText, int constant){
        mUserValue = Double.valueOf(editText);
        mCoinReference = mListRate.get(constant).getValue();
        setEuro(mCurrentValue, mUserValue, mCoinReference);

        mEuroValue = calculateDouble(mCurrentValue, mUserValue, mCoinReference);

        mUserValue = mEuroValue;
        mCoinReference = 1.0;
    }

    @Override
    public void afterTextChanged(Editable s) {


        if (mListRate.size() != 0) {
            if (mEuroEditText.getText().hashCode() == s.hashCode()) {
                if (mEuroEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {

                    mUserValue = Double.valueOf(mEuroEditText.getText().toString());
                    mCoinReference = 1.0;
                    setPounds(mUserValue, mCoinReference);

                    setUSDollar(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }

            } else if (mPoundEditText.getText().hashCode() == s.hashCode()) {
                if (mPoundEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {
                    generalSetEuro(mPoundEditText.getText().toString(), POUNDS);

                    setUSDollar(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }

            } else if (mDollarEditText.getText().hashCode() == s.hashCode()) {
                if (mDollarEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {
                    generalSetEuro(mDollarEditText.getText().toString(), DOLLAR_US);

                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }

            } else if (mYenEditText.getText().hashCode() == s.hashCode()) {
                if (mYenEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {
                    generalSetEuro(mYenEditText.getText().toString(), YEN);

                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }
            } else if (mBulgarianEditText.getText().hashCode() == s.hashCode()) {
                if (mBulgarianEditText.getText().toString().isEmpty()){
                    setEmptyField();
                } else {
                    generalSetEuro(mBulgarianEditText.getText().toString(), BULGARIAN);

                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }
            } else if (mCzechEditText.getText().hashCode() == s.hashCode()) {
                if (mCzechEditText.getText().toString().isEmpty()){
                    setEmptyField();
                } else {
                    generalSetEuro(mCzechEditText.getText().toString(), CZECH);
                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }
            } else if (mDanishEditText.getText().hashCode() == s.hashCode()) {
                if (mDanishEditText.getText().toString().isEmpty()){
                    setEmptyField();
                } else {
                    generalSetEuro(mDanishEditText.getText().toString(), DANISH);
                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }
            } else if (mHungarianEditText.getText().hashCode() == s.hashCode()) {
                if (mHungarianEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {
                    generalSetEuro(mHungarianEditText.getText().toString(), HUNGARIAN);
                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setPolish(mUserValue, mCoinReference);
                }
            } else if (mPolishEditText.getText().hashCode() == s.hashCode()) {
                if (mPolishEditText.getText().toString().isEmpty()) {
                    setEmptyField();
                } else {
                    generalSetEuro(mPolishEditText.getText().toString(), POLISH);
                    setUSDollar(mUserValue, mCoinReference);
                    setPounds(mUserValue, mCoinReference);
                    setYen(mUserValue, mCoinReference);
                    setBulgarian(mUserValue, mCoinReference);
                    setCzech(mUserValue, mCoinReference);
                    setDanish(mUserValue, mCoinReference);
                    setHungarian(mUserValue, mCoinReference);
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

    public void setBulgarian(double userValue, double coinReference) {
        mBulgarianEditText.removeTextChangedListener(this);
        mBulgarianEditText.setText(calculate(mListRate.get(BULGARIAN).getValue(), userValue, coinReference));
        mBulgarianEditText.addTextChangedListener(this);
    }

    public void setCzech(double userValue, double coinReference) {
        mCzechEditText.removeTextChangedListener(this);
        mCzechEditText.setText(calculate(mListRate.get(CZECH).getValue(), userValue, coinReference));
        mCzechEditText.addTextChangedListener(this);
    }

    public void setDanish(double userValue, double coinReference) {
        mDanishEditText.removeTextChangedListener(this);
        mDanishEditText.setText(calculate(mListRate.get(DANISH).getValue(), userValue, coinReference));
        mDanishEditText.addTextChangedListener(this);
    }

    public void setHungarian(double userValue, double coinReference) {
        mHungarianEditText.removeTextChangedListener(this);
        mHungarianEditText.setText(calculate(mListRate.get(HUNGARIAN).getValue(), userValue, coinReference));
        mHungarianEditText.addTextChangedListener(this);
    }

    public void setPolish(double userValue, double coinReference) {
        mPolishEditText.removeTextChangedListener(this);
        mPolishEditText.setText(calculate(mListRate.get(POLISH).getValue(), userValue, coinReference));
        mPolishEditText.addTextChangedListener(this);
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

        mBulgarianEditText.removeTextChangedListener(this);
        mBulgarianEditText.setText("");
        mBulgarianEditText.addTextChangedListener(this);

        mCzechEditText.removeTextChangedListener(this);
        mCzechEditText.setText("");
        mCzechEditText.addTextChangedListener(this);

        mDanishEditText.removeTextChangedListener(this);
        mDanishEditText.setText("");
        mDanishEditText.addTextChangedListener(this);

        mHungarianEditText.removeTextChangedListener(this);
        mHungarianEditText.setText("");
        mHungarianEditText.addTextChangedListener(this);

        mPolishEditText.removeTextChangedListener(this);
        mPolishEditText.setText("");
        mPolishEditText.addTextChangedListener(this);
    }
}
