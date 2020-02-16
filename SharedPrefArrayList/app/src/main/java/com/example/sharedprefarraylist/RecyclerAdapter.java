package com.example.sharedprefarraylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 7/8/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mViewHolder> {
    private ArrayList<RecyclerList> mRecyclerList;

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public TextView tv2;

        public mViewHolder(View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv_1);
            tv2 = itemView.findViewById(R.id.tv_2);
        }
    }

    public RecyclerAdapter(ArrayList<RecyclerList> exampleList){
        mRecyclerList = exampleList;
    }
    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        mViewHolder viewHolder = new mViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        RecyclerList currentItem = mRecyclerList.get(position);
        holder.tv1.setText(currentItem.getText1());
        holder.tv2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mRecyclerList.size();
    }
}
