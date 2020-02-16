package com.example.user.recyclerviewtutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by User on 3/10/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.textViewPrice.setText("$" + listItem.getPrice());

        holder.textOneHour.setText("1 HR:" + listItem.getOneHour() + "%");
        if (listItem.getOneHour().contains("-")) {
            holder.textOneHour.setTextColor(Color.parseColor("#e32636")); //crimson red
        } else {
            holder.textOneHour.setTextColor(Color.parseColor("#32cd32")); //forest green
        }

        holder.textViewTFHour.setText(" 24 HR:" + listItem.getTwentyfourHour() + "%");
        if (listItem.getTwentyfourHour().contains("-")) {
            holder.textViewTFHour.setTextColor(Color.parseColor("#e32636"));
        } else {
            holder.textViewTFHour.setTextColor(Color.parseColor("#32cd32"));
        }

        holder.textSevenDay.setText(" 7 Day:" + listItem.getSevendays() + "%");
        if (listItem.getSevendays().contains("-")) {
            holder.textSevenDay.setTextColor(Color.parseColor("#e32636"));
        } else {
            holder.textSevenDay.setTextColor(Color.parseColor("#32cd32"));
        }

         Glide.with(context).load("https://api.cryptocallback.com/images/" + listItem.getDesc() + ".png")

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView1);

        //listen for item click then open new activity
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CoinDetailActivity.class);
                intent.putExtra("Coin Name", listItem.getHead());
                intent.putExtra("Coin Sym", listItem.getDesc());
                intent.putExtra("Price", listItem.getPrice());
                intent.putExtra("1 Hour", listItem.getOneHour());
                intent.putExtra("24 Hour", listItem.getTwentyfourHour());
                intent.putExtra("7 Days", listItem.getSevendays());
                context.startActivity(intent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView textViewPrice;
        public TextView textOneHour;
        public TextView textViewTFHour;
        public TextView textSevenDay;
        public ImageView imageView1;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textOneHour = (TextView) itemView.findViewById(R.id.textOneHour);
            textViewTFHour = (TextView) itemView.findViewById(R.id.textViewTFHour);
            textSevenDay = (TextView) itemView.findViewById(R.id.textSevenDay);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
