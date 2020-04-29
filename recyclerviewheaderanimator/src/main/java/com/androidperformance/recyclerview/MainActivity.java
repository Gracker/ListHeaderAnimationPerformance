package com.androidperformance.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;

import com.androidperformance.recyclerview.dummy.DummyContent;
import com.androidperformance.recyclerview.ui.main.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mFAB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ItemFragment.newInstance(1) ,"ItemFragment")
                    .commitNow();
        }

        mFAB = findViewById(R.id.floatingActionButton);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ItemFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("ItemFragment"))).startAnimation();
            }
        });
    }

    public interface OnAnimationStartListener {
        // TODO: Update argument type and name
        void onAnimationStart();
    }
}
