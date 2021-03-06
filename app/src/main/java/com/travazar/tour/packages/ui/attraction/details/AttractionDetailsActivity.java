/*
 * Copyright (c) 2017.
 * Created by Joseph Mangmang on 10/18/17 5:07 PM
 */

package com.travazar.tour.packages.ui.attraction.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travazar.tour.packages.R;
import com.travazar.tour.packages.data.model.Attraction;
import com.travazar.tour.packages.data.model.Slider;
import com.travazar.tour.packages.ui.base.BaseActivity;
import com.travazar.tour.packages.ui.views.InfoView;
import com.travazar.tour.packages.ui.views.imageslider.ImageSlider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * Created by kali_root on 10/18/2017.
 */

public class AttractionDetailsActivity extends BaseActivity {
    public static final String EXTRA_ATTRACTION = "com.travazar.tour.packages.EXTRA_ATTRACTION";
    @BindView(R.id.image_slider)
    ImageSlider mImageSlider;
    @BindView(R.id.text_attraction_name)
    TextView mTitle;
    @BindView(R.id.rating_bar_attraction)
    RatingBar mRatingBar;
    @BindView(R.id.text_attraction_total_reviews)
    TextView mTotalReview;
    @BindView(R.id.text_attraction_address)
    TextView mAddress;
    @BindView(R.id.attraction_info_overview)
    InfoView mOverview;

    private MapFragment mMapSnapshot;
    private Attraction mAttraction;

    public static void launch(Context context, Attraction attraction) {
        checkNotNull(attraction);
        Intent intent = new Intent(context, AttractionDetailsActivity.class);
        intent.putExtra(EXTRA_ATTRACTION, attraction);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);
        ButterKnife.bind(this);
        showBackButton(true);
        mMapSnapshot = (MapFragment) getFragmentManager().findFragmentById(R.id.attraction_map_snapshot);
        checkNotNull(mMapSnapshot);

        mAttraction = getAttractionFromIntent();
        showAttractionDetails();
    }


    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    private Attraction getAttractionFromIntent() {
        return getIntent().getParcelableExtra(EXTRA_ATTRACTION);
    }

    private void showAttractionDetails() {
        if (mAttraction == null) return;
        List<Slider> sliders = new ArrayList<>();
        sliders.add(Slider.builder()
                .imageUrl(mAttraction.imageUrl())
                .build());

        mImageSlider.setDataList(sliders);
        mTitle.setText(mAttraction.name());
        mRatingBar.setRating((float) mAttraction.rating());
        mTotalReview.setText(mAttraction.totalReviews());
        mAddress.setText(mAttraction.getAddress());
        mOverview.setBody(mAttraction.overview());
        mMapSnapshot.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (googleMap != null) {
                    LatLng latLng = new LatLng(mAttraction.latitude(), mAttraction.longitude());
                    googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(mAttraction.getAddress()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f));
                }
            }
        });
    }

}
