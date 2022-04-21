package com.inah_wook.ex023listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //Adapter참조변수
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            listView = findViewById(R.id.listView);

            //어뎁터에게 내가 원하는 리스트의 파일과 그 모양을 알려줌 ~
            adapter = ArrayAdapter.createFromResource(this,R.array.datas,R.layout.list_item);
            // 어느 어뎁터인지 리스트뷰한테 설정해줌 ~
            listView.setAdapter(adapter);


            //리스트 뷰 항복(아이템 )을 클릭 시 반응ㅎ는 리스터 설정

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    // 난 뭘 클릭했는지 데이터를 알고 싶어 , , ,
                    // Resorce 창고 관리자 소환
                    Resources res = getResources();
                    String[] datas = res.getStringArray(R.array.datas);

                    Toast.makeText(MainActivity.this,datas[i], Toast.LENGTH_SHORT).show();
                }
            });

                //만일 길게 누를땐
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(MainActivity.this,i+ "번 째 롱클릭", Toast.LENGTH_SHORT).show();

                    return true; //이 리턴값이 false면 onItemClick()이 이어서 발동함
                }
            });






    }
}