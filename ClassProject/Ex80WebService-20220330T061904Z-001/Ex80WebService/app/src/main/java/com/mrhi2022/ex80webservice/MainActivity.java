package com.mrhi2022.ex80webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.mrhi2022.ex80webservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //activity_main.xml과 연결되는 바인딩 클래스 참조변수
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v->loadTextFile());
        binding.btn2.setOnClickListener(v->loadImageFile());
    }

    void loadTextFile(){
        //서버에서 제공하는 텍스트문서 읽어오기.. - 인터넷퍼미션 필요
        //네트워크작업은 무조건 별도 Thread가 해야 함.
        Thread t= new Thread(){
            @Override
            public void run() {
                //텍스트문서가 있는 서버의 URL 주소
                String address= "http://mrhi2022.dothome.co.kr/index.html";

                try {
                    //무지개로드(Stream)을 연결해주는 해임달(URL)객체 생성
                    URL url= new URL(address);
                    //무지개로드 열기
                    InputStream is= url.openStream(); //바이트 스트림
                    InputStreamReader isr= new InputStreamReader(is);//문자 스트림
                    BufferedReader reader= new BufferedReader(isr);//보조문자 스트림

                    StringBuffer buffer= new StringBuffer();
                    while(true){
                        String line= reader.readLine();
                        if(line==null) break;

                        buffer.append(line+"\n");
                    }//while..

                    //별도 Thread는 화면변경작업 불가
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.tv.setText(buffer.toString());
                        }
                    });

                    reader.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

    void loadImageFile(){

        //서버이미지를 쉽게 읽어와서 이미지뷰에 보여줄 수 있는 이미지로드 라이브러리
        String address="http://mrhi2022.dothome.co.kr/paris.jpg";
        Glide.with(this).load(address).into(binding.iv);

        //서버에서 이미지파일 읽어와서 이미지뷰에 보여주기
//        new Thread(){
//            @Override
//            public void run() {
//
//                String address="http://mrhi2022.dothome.co.kr/paris.jpg";
//
//                try {
//                    URL url= new URL(address);
//                    InputStream is= url.openStream();
//                    Bitmap bm= BitmapFactory.decodeStream(is);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.iv.setImageBitmap(bm);
//                        }
//                    });
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.start();


    }
}