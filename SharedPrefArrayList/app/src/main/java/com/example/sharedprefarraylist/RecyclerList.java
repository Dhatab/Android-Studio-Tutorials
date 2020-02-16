package com.example.sharedprefarraylist;

/**
 * Created by User on 7/8/2018.
 */

public class RecyclerList {
    private String mText1;
    private String mText2;

    public RecyclerList(String text1, String text2) {
        this.mText1 = text1;
        this.mText2 = text2;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

}
