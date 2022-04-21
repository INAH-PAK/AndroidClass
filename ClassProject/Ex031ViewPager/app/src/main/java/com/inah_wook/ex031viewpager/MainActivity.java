package com.inah_wook.ex031viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //뷰 페이저가 보여줄 페이지의 이미지 리소스ID 데이터를 가진 리스트
    ArrayList<Integer> imgIds = new ArrayList<>();

    ViewPager2 pager ;
    MyAdapter adapter ;

    Button btn_prev , btn_next ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가 ( 테스트 목적으로 페이지 별로 보여줄 이미지들 : 1페이지 당 한개의 이미지)
        imgIds.add((R.drawable.ch_chopa));
        imgIds.add((R.drawable.ch_luffy));
        imgIds.add((R.drawable.ch_nami));
        imgIds.add((R.drawable.ch_sandi));
        imgIds.add((R.drawable.ch_usoup));
        imgIds.add((R.drawable.ch_zoro));


        //이제 레이아웃가서 시안 만들기. page 레이아웃

        //이제 만들어 줄 아답터 생성

        pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this,imgIds);
        pager.setAdapter(adapter);


        //페이지 이동하는 투명 버튼 만들기
        btn_prev = findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //현재 페이지의 1 페이지 전
                int index = pager.getCurrentItem()-1;

                // 특정 페이지로 이동
                pager.setCurrentItem(index,true); //처음이랑 마지막번호 -1해도 걍 마지막 번호 지가 가지고 있음.


            }
        });


        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int index = pager.getCurrentItem()+1;

                pager.setCurrentItem(index,false);
            }
        });


    }
}