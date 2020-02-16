package com.example.user.recyclerviewtutorial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.recyclerviewtutorial.graph.graph;

import static android.icu.lang.UCharacter.toLowerCase;


public class CoinDetailActivity extends AppCompatActivity {
    private static Button button_alert;
    private ImageView coinIMG, graphIMG;
    private String coinName, coinSymbol, coinPrice, coinOneHR, coinTFHR, coinSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView header = findViewById(R.id.newtextViewHead);
        TextView symbol = findViewById(R.id.Symbol2);
        TextView price = findViewById(R.id.Price2);
        TextView price2 = findViewById(R.id.Hour2);
        TextView price3 = findViewById(R.id.TFHour2);
        TextView price4 = findViewById(R.id.Seven2);

        coinIMG = (ImageView) findViewById(R.id.imageView2);
        graphIMG = (ImageView) findViewById(R.id.graphimg);
        button_alert = (Button) findViewById(R.id.button);


        //Calls for intent
        Intent intent = getIntent();
        if ( intent == null)
            return;

        //Intent carry overs
        coinName = intent.getStringExtra("Coin Name");
        coinSymbol = intent.getStringExtra("Coin Sym");
        coinPrice = intent.getStringExtra("Price");
        coinOneHR = intent.getStringExtra("1 Hour");
        coinTFHR = intent.getStringExtra("24 Hour");
        coinSD = intent.getStringExtra("7 Days");

        //Set text
        header.setText(coinName);
        symbol.setText(coinSymbol);
        price.setText("$" + coinPrice);
        price2.setText("1 HR: " +coinOneHR);
        price3.setText("24 HR: " +coinTFHR);
        price4.setText("7 Days: " +coinSD);

        //image loader
        Glide.with(this).load("https://api.cryptocallback.com/images/" + coinSymbol + ".png")

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(coinIMG);

        //image loader
        Glide.with(this).load("https://cryptohistory.org/charts/candlestick/dash-usdt/7d/png")

                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(graphIMG);

        //Button alert method
        OnButtonClickListener();
    }


    public void OnButtonClickListener() {
        button_alert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CoinDetailActivity.this, graph.class));
                    }
                });

    }
}