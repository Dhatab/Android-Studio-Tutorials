package com.example.spannabletext;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        String text = "I want to change this color, make this clickable, and make this bold with italics and line";

        SpannableString ss = new SpannableString(text);

        //SS builder lets you change text and style unlike SS
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);




        //Sets color to certain word
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);

        //for background color
        BackgroundColorSpan fcsGreen = new BackgroundColorSpan(Color.GREEN);

        //For TypeFace
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italic = new StyleSpan(Typeface.ITALIC);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        //Clicable Span
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clickable",Toast.LENGTH_SHORT).show();
            }

            //use to change drawstate of text
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ssb.append(" ...........this is appended");


        ssb.setSpan(fcsRed,22,27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(fcsGreen,22,27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(clickableSpan,39,48,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(boldSpan,64,68,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(italic,74,81,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(underlineSpan,86,90,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(strikethroughSpan,86,90,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ssb);

        //need to make clickable
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
