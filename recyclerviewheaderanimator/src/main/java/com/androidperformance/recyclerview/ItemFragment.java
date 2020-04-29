package com.androidperformance.recyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.AbsListView;

import com.androidperformance.recyclerview.dummy.DummyContent;
import com.androidperformance.recyclerview.dummy.DummyContent.DummyItem;

import java.util.List;

public class ItemFragment extends Fragment implements MainActivity.OnAnimationStartListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private  Context mContext;
    private View mHeaderView;
    private HeightView mHeaderHeightView;
    private ObjectAnimator mHeaderHeightEnterAnimator;
    private AnimatorSet mEnterSet;
    private Interpolator mCubicBezierEnterInterpolator;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);


        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            mHeaderView = new View(getContext());
            mHeaderView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(context,DummyContent.ITEMS,mHeaderView));
            initAnimators();
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void startAnimation(){
        mEnterSet.start();
    }

    @Override
    public void onAnimationStart() {
        mEnterSet.start();
    }
}
