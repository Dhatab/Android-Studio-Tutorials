package com.example.customtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {
    private Button toast_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast_btn = (Button) findViewById(R.id.toast_btn);
        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customToast(view);
            }
        });
    }

    public void customToast(View v){
        StyleableToast.makeText(this,"This is a custom toast", R.style.customToast).show();
    }
}
