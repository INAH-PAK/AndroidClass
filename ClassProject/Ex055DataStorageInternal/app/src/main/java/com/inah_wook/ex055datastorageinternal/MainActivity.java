package com.inah_wook.ex055datastorageinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_save).setOnClickListener(v-> {
            saveData();
        });
        findViewById(R.id.btn_load).setOnClickListener(v->loadData());


    }

    void saveData()  {
        //저장할 데이터들
        String data  = et.getText().toString();
        et.setText("");

        //이제 내장메모리에 저장하자 ~
        //다행히도 ~
        // 액티비티 클래스는 이미 내장메모지(Internal) 에 File 을 저장할 수 있도록
        //무지개로드 (Stream) 을 열 수 있는 기능 메소드가 존재 함.

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data.txt",MODE_APPEND);
            //fos 는 바이트 단위로 데이터를 보내야 하기때문에 우리가 사용하는 String으론 쓰기 불편함.
            //그래서 문자단위 스트림으로 변환. + 보조문자스트림 쓰면 편해짐.
            PrintWriter writer = new PrintWriter(fos);
            // 이러면 마치 자바때, System.out.println() 기능처럼 파일에 데이터를 쓰는 방식.
            writer.println(data);
            writer.flush();
            writer.close();

            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    void loadData(){
        //불러올 데이터

        FileInputStream fis = new FileInputInort("DAta.set");
        BufferedReader reader = new BufferedReader(fis);
        InputSream


        String line = reader.readLine();
        while(true){

            String line = reader.readLine();
            // 리드 라인 해오면 줄바꿈 문자는 빼고 가져 옴
            -> 줄바꿈 문자는 빼고 가져옴


        }

    }





}