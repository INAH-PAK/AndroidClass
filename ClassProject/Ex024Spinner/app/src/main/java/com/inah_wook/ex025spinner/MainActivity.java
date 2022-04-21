package com.inah_wook.ex025spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Spinner spinner;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        // 스피너 항목 모양을 디자인 하고 싶다면?
        //직접 아답터를 만들어 적용해야 함 !
        // 리스트 뷰와 마찬가지로 혼자서 화면을 구성하지 못함 !
        adapter = ArrayAdapter.createFromResource(this,R.array.city,R.layout.spinner);

        //드롭다운 된 항목들의 모양만 별도로 지정하는 것 도 가능,
        //어떤 모양으로 드롭다운 되는가 를 말해주는 건데ㅡ
        //아까 레이아웃 하나 텍스트 뷰로 만등고 있잖아?
        // 작은 박스에 텍스트만 담은 것.
        // 그 작은 사각박스가 나온다는 말 !
        adapter.setDropDownViewResource(R.layout.item_dropdown);

        //이제 끝 ! 스피너에게 아답터 설정

        spinner.setAdapter(adapter);

        //스피너를 선택했을 때 반응하는 리스너 객체 생성
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,i+ "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
}