package com.thedeveloperworldisyours.unitconverterpro.area;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thedeveloperworldisyours.unitconverterpro.R;
import com.thedeveloperworldisyours.unitconverterpro.common.dragandswipehelper.ItemTouchHelperAdapter;
import com.thedeveloperworldisyours.unitconverterpro.common.dragandswipehelper.ItemTouchHelperViewHolder;
import com.thedeveloperworldisyours.unitconverterpro.common.dragandswipehelper.OnStartDragListener;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.area.Area;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.area.AreaDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by javierg on 13/10/2016.
 */

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private static final String TAG = "Drag";

    private final List<Area> mItems = new ArrayList<>();

    private final OnStartDragListener mDragStartListener;
    AreaDataSource mAreaDataSource;

    public AreaAdapter(Context context, OnStartDragListener dragStartListener, List<Area> list) {
        mDragStartListener = dragStartListener;
        mItems.addAll(list);
        mAreaDataSource = new AreaDataSource(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_swipe_list_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position).getName());

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        mAreaDataSource.open();
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        sortList();
        List<Area> areasDelete = mAreaDataSource.getAllAreas();
        mAreaDataSource.close();
        return true;
    }

    public void sortList() {
        for (int i=0; i< mItems.size(); i++){
            mAreaDataSource.changePosition(mItems.get(i), i);//mItems.get(i).getId(),mItems.get(i).getPosition());
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;
        public final ImageView handleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.drag_swipe_list_item_text);
            handleView = (ImageView) itemView.findViewById(R.id.drag_swipe_list_item_handle);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
