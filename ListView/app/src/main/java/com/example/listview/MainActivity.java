package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listview);

        //Array List Way
        ArrayList<String> values = new ArrayList<>();
        values.add("One");
        values.add("Two");
        values.add("Three");
        values.add("Four");
        values.add("Five");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, values);

        //String Array Way
        /*
        String[] values = new String[] { "Android List View", "Adapter implementation", "Simple List View In Android", "Create List View Android",
                "Android Example", "List View Source Code", "List View Array Adapter", "Android Example List View"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);*/


        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }
}
