package com.androidperformance.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidperformance.recyclerview.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View mHeaderView;
    private final List<DummyItem> mValues;
    private static final int ITEM_HEADER = -1;
    private static final int ITEM_NON_HEADER = -2;
    private List<String> mList = new ArrayList<>();

    public MyItemRecyclerViewAdapter(Context context,List<DummyItem> items, View mHeaderView) {
        mValues = items;
        this.mHeaderView = mHeaderView;
        mList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.country_name)));
        mList.add(0, "");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            return new HeaderViewHolder(mHeaderView);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_simple_list_item, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(position > 0) {
            ((MyViewHolder) holder).textView.setText(mList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_HEADER;
        }else {
            return ITEM_NON_HEADER;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

}
