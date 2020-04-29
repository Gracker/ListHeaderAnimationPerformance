package com.androidperformance.listviewheaderanimator;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter implements ListAdapter {
    private List<String> mList ;
    private static final int ITEM_HEADER = -1;
    private static final int ITEM_NON_HEADER = -2;
    private View mHeaderView;

    public ListViewAdapter(ArrayList list , View headerView){
        mList = list;
        mHeaderView = headerView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            int viewType = getItemViewType(position);
            if (viewType == ITEM_HEADER) {
                convertView = mHeaderView;
                return convertView;
            } else {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_simple_list_item, null);
                holder.name = (TextView)convertView;
                convertView.setTag(holder);
            }
        }
        holder.name.setText(mList.get(position));
        return convertView;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    class HeaderViewHolder {
        HeaderViewHolder(View headerView){
        }
    }

    class ViewHolder {
        TextView name; // declare your your_layout.xml view type
    }
}
