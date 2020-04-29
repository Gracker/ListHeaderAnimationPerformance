package com.androidperformance.listviewheaderanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View mHeaderView;
    private HeightView mHeaderHeightView;
    private ListView mListView;
    private static final int ITEM_HEADER = -1;
    private static final int ITEM_NON_HEADER = -2;
    private ArrayList<String> mList = new ArrayList<>();
    private ObjectAnimator mHeaderHeightEnterAnimator;
    private AnimatorSet mEnterSet;
    private Interpolator mCubicBezierEnterInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView =  findViewById(R.id.listview);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEnterSet.start();
            }
        });

        mList.addAll(Arrays.asList(getResources().getStringArray(R.array.country_name)));
        mList.add(0, "");

        mHeaderView = new View(this);
        mHeaderView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));

        mHeaderHeightView = new HeightView(mHeaderView);
        mListView.setAdapter(new ListViewAdapter(mList,mHeaderView));
        initAnimators();
    }

    private  void initAnimators(){
        mCubicBezierEnterInterpolator = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        mHeaderHeightView = new HeightView(mHeaderView);
        mHeaderHeightEnterAnimator = ObjectAnimator.ofInt(mHeaderHeightView,"height", 500, 0);
        mEnterSet = new AnimatorSet();
        mEnterSet.playTogether(mHeaderHeightEnterAnimator );
        mEnterSet.setDuration(500);
        mEnterSet.setInterpolator(mCubicBezierEnterInterpolator);
    }





}
