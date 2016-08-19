package com.thedeveloperworldisyours.unitconverterpro.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thedeveloperworldisyours.unitconverterpro.MainActivity;
import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.model.Currency;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenter;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenterImpl;
import com.thedeveloperworldisyours.unitconverterpro.view.CurrencyView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements CurrencyView{

    @BindView(R.id.fragment_currency_progressbar)
    ProgressBar mProgressBar;

    @BindView(R.id.fragment_currency_euro_input_layout)
    TextInputLayout mEuroInputLayout;

    @BindView(R.id.fragment_currency_dollar_input_layout)
    TextInputLayout mDollarInputLayout;

    @BindView(R.id.fragment_currency_pound_input_layout)
    TextInputLayout mPoundInputLayout;

    private View mView;

    private CurrencyPresenter mCurrencyPresenter;

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

        ButterKnife.bind(this, mView);
        mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.currency_color), android.graphics.PorterDuff.Mode.MULTIPLY);
        mCurrencyPresenter = new CurrencyPresenterImpl(this);
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
        mProgressBar.setVisibility(View.GONE);
        mEuroInputLayout.setVisibility(View.VISIBLE);
        mDollarInputLayout.setVisibility(View.VISIBLE);
        mPoundInputLayout.setVisibility(View.VISIBLE);
    }
}
