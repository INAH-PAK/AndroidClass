package com.inah_wook.ex034fragmentpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

        ViewPager2 pager ; // 얘를 보여줄 아답터
        MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pager =findViewById(R.id.pager);
        adapter = new MyAdapter(this);
        pager.setAdapter(adapter);

    }
}