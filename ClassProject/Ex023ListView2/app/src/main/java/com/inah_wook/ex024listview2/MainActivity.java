package com.inah_wook.ex024listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> datas= new ArrayList<>(); //대량의 리스트를 저장 할 리스트 객체

    ListView listView ; //상위 위치한 리스트 객체
    ArrayAdapter adapter ;   //리스트 객체를 만들기 위한 일꾼 :  어뎁터 뷰

    EditText et ;
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가 -> 실무에서는 서버나 DB에서 읽어옴.
        // 테스트 목적으로 String 객체를 직접 추가 !
        datas.add(new String("aaa"));
        datas.add(new String("bbb"));
        datas.add("ccc");  //자동 new String();

        //대량의 데이터를 xml 레이아웃 모양의 뷰 객체들로 만들어 주는 늘력을 가진
        // 어댑터 객체 생성
        adapter = new ArrayAdapter(this,R.layout.listview_items,datas);
        listView = findViewById(R.id.lv);
        listView.setAdapter(adapter);

        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //et에 써있는 글씨를 얻어와서 리스트 뷰에 항목 추가해서 보여주기
                String data = et.getText().toString();

                //새로운 아이템은 리스트뷰나 아답터에 추가하는 것이 아니라
                //아답터에게 전달한 대량의 데이터(리스트 : datas) 에게 추가하면
                //아답터가 알아서 추가로 뷰를 만들어서 리스트 뷰한테 전달.

                datas.add(data);

                //아답터에게 바꼈다고 알려줘야 화면이 갱신됨 ~
                adapter.notifyDataSetChanged();

                //리스트 뷰의 스크롤을 마지막으로 ~
                listView.setSelection(datas.size()-1);

                et.setText("");


            }
        });

        // 리스트 뷰 항목을 롱 클릭해서 해당 항목 삭제 !

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //클릭한 위치 (position - 세번째 파라미터인 i)의 데이터를 삭제 !
                datas.remove(i);
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        TextView tv = findViewById(R.id.tv);
        listView.setEmptyView(tv);



    }
}