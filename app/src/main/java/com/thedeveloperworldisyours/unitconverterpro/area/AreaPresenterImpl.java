package com.thedeveloperworldisyours.unitconverterpro.area;

import android.content.Context;

import com.thedeveloperworldisyours.unitconverterpro.sqlite.area.Area;
import com.thedeveloperworldisyours.unitconverterpro.sqlite.area.AreaDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierg on 17/10/2016.
 */

public class AreaPresenterImpl implements AreaPresenter {

    AreaDataSource mAreaDataSource;
    AreaView mAreaView;
    int mTotal;

    @Override
    public void refresh(Context context, AreaView areaView) {
        mAreaDataSource = new AreaDataSource(context);
        mAreaView = areaView;
        create();
        mAreaView.showInfo(sortList());
        mAreaDataSource.close();

    }

    public void create() {

        mAreaDataSource.open();
        mTotal = mAreaDataSource.getAllAreas().size();
        if (mTotal <= 0) {
            mAreaDataSource.createArea("kilometre", 1L, 6);
            mAreaDataSource.createArea("hectare", 100L, 1);
            mAreaDataSource.createArea("metre", 1000000L, 2);
            mAreaDataSource.createArea("centimetre", 10000000000L, 3);
            mAreaDataSource.createArea("millimetre", 1000000000000L, 4);
            mAreaDataSource.createArea("mile", (long) Double.parseDouble("0.3861022"), 5);
            mAreaDataSource.createArea("acre", (long) Double.parseDouble("247.1054"), 0);
        }
    }

    public List<Area> sortList() {
        List<Area> areasDelete = mAreaDataSource.getAllAreas();
        mTotal = mAreaDataSource.getAllAreas().size();
        List<Area> areas = new ArrayList<>();
        for (int i=0; i<mTotal; i++){
            areas.add(i, mAreaDataSource.getAreaWhenPosition(i));
        }
        return areas;

    }

}
