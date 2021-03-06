package com.travazar.tour.packages.ui.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.travazar.tour.packages.R;
import com.travazar.tour.packages.TravazarApplication;

/**
 * Created by kali_root on 10/12/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        reloadToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        reloadToolbar();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void reloadToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new RuntimeException("Toolbar not found");
        }
        setSupportActionBar(mToolbar);
    }

    protected void showBackButton(boolean show) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(show);
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected <F extends Fragment> F switchFragment(F fragment, String tag) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment, tag)
                .commit();
        return fragment;
    }

    protected <F extends Fragment> F switchFragment(F fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
        return fragment;
    }

    protected TravazarApplication getApp() {
        return ((TravazarApplication) getApplication());
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
