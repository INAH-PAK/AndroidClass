package com.inah_wook.ex030recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터 !! 만들자
    ArrayList<Item> items = new ArrayList<>();

    Button btnLinear, btnGrid, btnAdd,btnDelete;


RecyclerView recyclerView;
MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 대량의 데이터는 실무에선 서버나 DB에서 긁어옴
        //우린 테스트 목적으로 추가 !

        items.add( new Item("루피","해적단 선장",R.drawable.ch_luffy,R.drawable.img01));
        items.add( new Item("조로","해적단 부선장",R.drawable.ch_zoro,R.drawable.img02));
        items.add( new Item("나미","해적단 선장",R.drawable.ch_zoro,R.drawable.img03));
        items.add( new Item("상디","해적단 선장",R.drawable.ch_sandi,R.drawable.img04));
        items.add( new Item("우솝","해적단 선장",R.drawable.ch_usoup,R.drawable.img05));
        items.add( new Item("쵸파","해적단 선장",R.drawable.ch_chopa,R.drawable.img01));
        items.add( new Item("니코로빈","해적단 선장",R.drawable.ch_nami,R.drawable.img06));

        recyclerView = findViewById(R.id.recycler);
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        //선형배치 버튼 클릭
        btnLinear = findViewById(R.id.btn_linear);
        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이틀러뷰의 대치관리자를 layout manager를 버튼 리니어 눌렀을때 변경!
                //리버스 레이아웃은 최신순 정렬시 위로 올라가는것과 같음 ~
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,true);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

                    //격자배치 버튼 클릭
            btnGrid = findViewById(R.id.btn_grid);
            btnGrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {// spanCount :  디폴트 값이 horizantal .== 옆으로 씀
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2,RecyclerView.VERTICAL,true);
                    recyclerView.setLayoutManager(gridLayoutManager);

                }
            });

            //항목 추가 버튼
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리사이클러 뷰에 아이템을 직접 추가하는 것이 아님!
                Item item = new Item("NEW","new face",R.drawable.img10,R.drawable.img12);


               // items.add(item);  그냥 에드하면 뒤에 붙음
                // 근데 실무에선 추가하면 맨 앞에 추가도ㅓㅣ잖아?
                items.add(0,item );

                //데이터의 변경이 생기면 반드시 아답터에세 변경사실을 공지 (notify) 해야 화면이 갱신됨
                //adapter.notifyDataSetChanged();
                // 근데 이렇게 하면 전체를 다시 리로드해서 낭비임

               //  adapter.notifyItemInserted( items.size()-1); 이건 맨 뒤에 추가 할 때임.
                adapter.notifyItemInserted( 0);
                 //이제 리사리클러의 스크롤을 첫 번째 위치로 이동
                recyclerView.scrollToPosition(0);




            }
        });


            btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(0);
                adapter.notifyItemRemoved(0);



            }
        });


        //리사이클러뷰으ㅏ 아이템뷰를 클릭할땨 반응하는 리스터는 없음.
        // 그래서아이템뷰는 직접클릭리스너를 설정
        // MyAdapter 에서 코딩 ㄱ






    }
}