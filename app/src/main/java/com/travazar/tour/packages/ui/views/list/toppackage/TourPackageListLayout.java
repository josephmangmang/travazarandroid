package com.travazar.tour.packages.ui.views.list.toppackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.travazar.tour.packages.R;
import com.travazar.tour.packages.data.model.TourPackage;
import com.travazar.tour.packages.ui.views.list.base.ListViewLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kali_root on 10/12/2017.
 */

public class TourPackageListLayout extends ListViewLayout {
    List<TourPackage> mTourPackages;
    private TopTourPackageAdapter mAdapter;

    public TourPackageListLayout(Context context) {
        super(context);
    }

    public TourPackageListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TourPackageListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onPrepareRecyclerView() {
        mTourPackages = new ArrayList<>();
        if (true || isInEditMode()) {
            mTourPackages.add(null);
            mTourPackages.add(null);
            mTourPackages.add(null);
        }
        mAdapter = new TopTourPackageAdapter(mTourPackages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }
}