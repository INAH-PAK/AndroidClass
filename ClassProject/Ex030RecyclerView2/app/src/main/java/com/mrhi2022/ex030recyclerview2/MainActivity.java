package com.mrhi2022.ex030recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터들
    ArrayList<Item> items= new ArrayList<>();

    RecyclerView recyclerView;
    MyAdapter adapter;

    Button btnLinear, btnGrid;
    Button btnAdd,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터들 추가 -실무에서는 서버나 DB에서 읽어옴.
        //테스트목적으로 직접 추가
        items.add( new Item("루피", "해적단 선장", R.drawable.crew_luffy, R.drawable.bg_one01) );
        items.add( new Item("조로", "해적단 부선장", R.drawable.crew_zoro, R.drawable.bg_one02) );
        items.add( new Item("나미", "해적단 항해사", R.drawable.crew_nami, R.drawable.bg_one03) );
        items.add( new Item("상디", "해적단 요리사", R.drawable.crew_sanji, R.drawable.bg_one04) );
        items.add( new Item("우솝", "해적단 저격수", R.drawable.crew_usopp, R.drawable.bg_one05) );
        items.add( new Item("쵸파", "해적단 의사", R.drawable.crew_chopper, R.drawable.bg_one06) );
        items.add( new Item("니코로빈", "해적단 고고학자", R.drawable.crew_nicorobin, R.drawable.bg_one07) );

        recyclerView= findViewById(R.id.recycler);
        adapter= new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        //선형배치 버튼 클릭
        btnLinear= findViewById(R.id.btn_linear);
        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰의 배치관리자(LayoutManager)를 변경하기
                LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
            }
        });

        //격자배치 버튼 클릭
        btnGrid= findViewById(R.id.btn_grid);
        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridLayoutManager layoutManager= new GridLayoutManager(MainActivity.this, 2);//옆으로 2칸짜리 격자
                recyclerView.setLayoutManager(layoutManager);
            }
        });


        //항목 추가 버튼
        btnAdd= findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러뷰에 아이템을 직접 추가하는 것이 아님!!!
                Item item= new Item("NEW","해적단 NEW", R.drawable.bg_one08, R.drawable.bg_one09);
                items.add(0,item); //새로운 Item을 무조건 첫번째 요소로 추가

                //데이터의 변경이 생기면 반드시 아답터에게 변경사실을 공지(notify)해야 화면이 갱신됨
                //adapter.notifyDataSetChanged(); //전체 화면을 갱신하여 효율이 나쁨
                adapter.notifyItemInserted(0);
                //리사이클러뷰의 스크롤을 첫번째 위치로 이동
                recyclerView.scrollToPosition(0);
            }
        });

        //항목 삭제 버튼
        btnDelete= findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //리사이클러뷰의 아이템뷰를 삭제하는 것이아니라. List에서 Item제거
                items.remove(0);
                adapter.notifyItemRemoved(0);

            }
        });

        //리사이클러뷰의 아이템뷰를 클릭할때 반응하는 리스너는 없음.
        //그래서. 아이템뷰에 직접 클릭리스너를 설정
        //MyAdapter.java에서 itemView가 만들어 지기에 그 곳에서 코딩


    }
}