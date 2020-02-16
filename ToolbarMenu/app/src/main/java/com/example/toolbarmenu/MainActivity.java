package com.example.toolbarmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //inflates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    //used for clicking on the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:
                Toast.makeText(this,"Item 1 Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_2:
                Toast.makeText(this,"Item 2 Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_3:
                Toast.makeText(this,"Item 3 Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sub_item:
                Toast.makeText(this,"Item Sub Selected", Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onOptionsItemSelected(item);

    }
}
