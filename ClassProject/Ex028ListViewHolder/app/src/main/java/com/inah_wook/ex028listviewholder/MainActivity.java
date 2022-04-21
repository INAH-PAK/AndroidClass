package com.inah_wook.ex028listviewholder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();

    ListView listView;
    MyAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add( new  String("aaa"));
        items.add( new  String("bbb"));
        items.add( new  String("ccc"));
        items.add( new  String("ddd"));
        items.add( new  String("eee"));


        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this,items);
        listView.setAdapter(adapter);








    }
}