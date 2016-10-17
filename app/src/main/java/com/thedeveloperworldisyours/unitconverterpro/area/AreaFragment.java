package com.thedeveloperworldisyours.unitconverterpro.area;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.common.dragandswipehelper.OnStartDragListener;
import com.thedeveloperworldisyours.unitconverterpro.common.dragandswipehelper.SimpleItemTouchHelperCallback;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.area.Area;

import java.util.List;

/**
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment implements OnStartDragListener, AreaView {

    private ItemTouchHelper mItemTouchHelper;
    private RecyclerView mRecyclerView;

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
        View view = inflater.inflate(R.layout.fragment_area, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_area_recycler_view);
        AreaPresenter areaPresenter = new AreaPresenterImpl();
        areaPresenter.refresh(getActivity(), this);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void showInfo(List<Area> list) {
        buildingList(list);
    }

    public void buildingList(List<Area> list) {
        AreaAdapter adapter = new AreaAdapter(getActivity(), this, list);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}
