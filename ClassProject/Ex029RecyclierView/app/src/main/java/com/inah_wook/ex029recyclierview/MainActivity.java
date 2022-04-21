package com.inah_wook.ex029recyclierview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //recyclierview 는 포트폴리오에 무조건 들어가야 함.


    ArrayList<Item> items = new ArrayList<>();

    RecyclerView recyclerView;
    MyAdapter adapter;

    //대량의 데이터를 추가.
    //당연히 실무에선 서버나 DB에서 추가해 옴,
    //우린 테스트 목적으로 직접 추가.

    //리사이클러 뷰를 생성하는 역할  : 아답터.
    //리사이클러뷰가 아이템을 화면에 표시할 때, 아이템뷰들이 리사이클러뷰 내부에 배치되는 형태를 광리하는 요소

    // 1. 메인엑티비티에 리사이클러뷰 추가
    // 2. 어떻게 리스트를 나타낼지, 각 아이템 하나의 요소 레이아웃 추가
    // 3. 리사이클러 뷰를 생성해 줄 리사이클러 아답터 생성 -> MyAdapter.java
    // 4. main.java 에서 set 아답터, 레이아웃 ㄱㄱ



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add( new Item("sam", "Hello") );
        items.add( new Item("inah", "gg") );
        items.add( new Item("a", "aa") );
        items.add( new Item("b", "dd") );
        items.add( new Item("c", "ff") );
        items.add( new Item("d", "Hevllo") );
        items.add( new Item("e", "Hello") );
        items.add( new Item("h", "Hsfello") );
        items.add( new Item("f", "Helsfdlo") );


        recyclerView = findViewById(R.id.recycle);
        adapter = new MyAdapter(this,items);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰는 아이템 클릭시 반응하는 리스너 같은 것이 없음.
        // 방법은 ? ItemView (항목뷰) 에 직접 클릭리스너를 설정하여 처리함.
        // 어뎁터 ㄱㄱ MyAdapter.class 에서 직접 코딩해야 함. -> 이게 리사이클러 뷰의 단점


    }
}