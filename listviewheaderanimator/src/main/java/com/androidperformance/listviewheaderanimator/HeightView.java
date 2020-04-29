package com.androidperformance.listviewheaderanimator;

import android.view.View;
import android.view.ViewGroup;

public class HeightView {

    private View view;

    public HeightView(View view) {
        this.view = view;
    }

    public void setHeight(int height) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
        }
    }

    public int getHeight() {
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }
}
