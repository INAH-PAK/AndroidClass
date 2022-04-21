package com.inah_wook.ex027listviewcustom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // 대량의 데이터를 가지는 리스트 객체 생성
    ArrayList<Item> items = new ArrayList<>();

    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터를 추가 - 실무에서는 서버나 DB에서 읽어옴.
        //테스트목적으로 직접 아이템을 추가
        items.add( new Item("전현무", "대한민국", R.drawable.flag_korea)  );
        items.add( new Item("기욤패트리", "캐나다", R.drawable.flag_canada)  );
        items.add( new Item("타일러", "미국", R.drawable.flag_usa)  );
        items.add( new Item("알베르토", "이탈리아", R.drawable.flag_italy)  );
        items.add( new Item("타쿠야", "일본", R.drawable.flag_japan)  );
        items.add( new Item("전현무", "대한민국", R.drawable.flag_korea)  );
        items.add( new Item("기욤패트리", "캐나다", R.drawable.flag_canada)  );
        items.add( new Item("타일러", "미국", R.drawable.flag_usa)  );
        items.add( new Item("알베르토", "이탈리아", R.drawable.flag_italy)  );
        items.add( new Item("타쿠야", "일본", R.drawable.flag_japan)  );

        listView = findViewById(R.id.listview);
        adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 //i : 클릭한 항목의 위치 position

                //해당 항목 Item 의 name 변수값 알아오기

                String name = items.get(i).name;

                //AlertDialog를 만들어주는 건축가(Builder)객체 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(name +"을 클릭했습니다.");
                builder.create().show();





            }
        });

    }
}