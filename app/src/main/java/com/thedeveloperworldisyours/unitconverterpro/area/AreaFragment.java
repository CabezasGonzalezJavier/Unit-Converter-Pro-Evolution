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

/**
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

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

        DragAndSwipeRecyclerListAdapter adapter = new DragAndSwipeRecyclerListAdapter(getActivity(), this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_area_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
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
}
