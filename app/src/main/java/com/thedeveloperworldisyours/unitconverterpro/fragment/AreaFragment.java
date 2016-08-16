package com.thedeveloperworldisyours.unitconverterpro.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thedeveloperworldisyours.unitconverterpro.R;

/**
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment {

    public AreaFragment() {
        // Required empty public constructor
    }

    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
