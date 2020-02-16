package com.example.user.recyclerviewtutorial;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by User on 3/10/2018.
 */

public class ListItem {

    private String head;
    private String desc;
    private String price;
    private String oneHour;
    private String sevendays;
    private String twentyfourHour;


    public ListItem(String head, String desc, String price, String oneHour, String sevendays, String twentyfourHour) {
        this.head = head;
        this.desc = desc;
        this.price = price;
        this.oneHour = oneHour;
        this.sevendays = sevendays;
        this.twentyfourHour = twentyfourHour;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getOneHour() {
        return oneHour;
    }

    public String getSevendays() {
        return sevendays;
    }

    public String getTwentyfourHour() {
        return twentyfourHour;
    }
}