package com.example.togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private TextView textView, textView1;
    private ToggleButton toggleButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggle);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        textView = (TextView) findViewById(R.id.TextView);
        textView1 = (TextView) findViewById(R.id.TextView1);

        onToggleChange();
    }

    public void checkButton(View v) {
        //set an id
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        if (radioButton.getText().toString().equals("On")){
            textView1.setVisibility(View.VISIBLE);
        } else{
            textView1.setVisibility(View.INVISIBLE);
        }
    }

    public void onToggleChange(){
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    textView.setVisibility(View.VISIBLE);
                }
                else {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
