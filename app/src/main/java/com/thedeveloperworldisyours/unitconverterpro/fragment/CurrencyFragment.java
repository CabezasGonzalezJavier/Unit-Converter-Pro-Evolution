package com.thedeveloperworldisyours.unitconverterpro.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thedeveloperworldisyours.unitconverterpro.MainActivity;
import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenter;
import com.thedeveloperworldisyours.unitconverterpro.presenter.CurrencyPresenterImpl;
import com.thedeveloperworldisyours.unitconverterpro.view.CurrencyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements CurrencyView{

    @BindView(R.id.fragment_currency_progressbar)
    ProgressBar mProgressBar;

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
        View view = inflater.inflate(R.layout.fragment_currency, container, false);

        ButterKnife.bind(this, view);
        mCurrencyPresenter = new CurrencyPresenterImpl(this);
        mCurrencyPresenter.callService();

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void successful() {
        Toast.makeText(getActivity(), getString(R.string.fragment_currency_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(getActivity(), getString(R.string.fragment_currency_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void generalError() {
        Toast.makeText(getActivity(), getString(R.string.fragment_currency_general_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
//        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
    }
}
