package com.example.nightmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Switch mSwitch;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref= new SharedPref(this);
        if(sharedPref.loadState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitch = (Switch)findViewById(R.id.switch_btn);
        if(sharedPref.loadState()==true){
            mSwitch.setChecked(true);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sharedPref.setTheme(true);
                    refreshApp();
                }else{
                    sharedPref.setTheme(false);
                    refreshApp();
                }
            }
        });
    }

    private void refreshApp() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
