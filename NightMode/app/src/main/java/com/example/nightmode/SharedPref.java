package com.example.nightmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

/**
 * Created by User on 7/2/2018.
 */

public class SharedPref {

    SharedPreferences sharedPreferences;
    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    //Use this method to save the theme with boolean
    public void setTheme(Boolean state){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    //This method will then load the theme state
    public Boolean loadState(){
        Boolean state = sharedPreferences.getBoolean("NightMode", false);
        return state;
    }
}
