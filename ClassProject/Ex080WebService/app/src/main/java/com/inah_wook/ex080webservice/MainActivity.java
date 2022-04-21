package com.inah_wook.ex080webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.inah_wook.ex080webservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // 이 예제는 내 서버에서 택스트와 이미지를 가져오는 걸 하는 예제.

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v->loadTextFile());
        binding.btn2.setOnClickListener(v->loadImageFile());


    }


    void loadTextFile(){
        // 네트워크 작업은 오래걸려서 반드시 별도의 스레드가 해야 함.
       Thread t = new Thread(){

           @Override  // 여기서 이 스레드가 할 작업을 줌
           public void run() {
               // 일단 텍스트 문자가 있는 서버의 URL 필요
               String myAddress = "http://webserver.dothome.co.kr/index.html";

////////////////////////여기 무조건 외우듯 써야 함 ////////////////////////////////////////////////////////
               try {
                   // 이제 서버와 내 컴터를 연결하자 ! 무지개로드 = 스트림 ! 해임달 = url !
                   // 먼저 무지개로드 만들 해임달 고용
                   URL url = new URL(myAddress);
                   // 그 다음 무지개 로드 만들고 열기 ~
                   InputStream is = url.openStream(); // 바이트 스트림으로 옴
                   InputStreamReader isr = new InputStreamReader(is); // 바이트를 한글자씩으로 바꾸기 ~
                   //여러글자 모아서 한글자씩 주는 애 고용
                   BufferedReader reader = new BufferedReader(isr); // 보조문자 스트림

                  StringBuffer stringBuffer = new StringBuffer(); // 밑의 반복문으로 받은 한 문장들을 추가할 객체
                   while(true){
                       String line =    reader.readLine(); // 한 글자씩 읽어들이는 명령
                    if(line==null) break;

                       stringBuffer.append(line+"\n"); //
                   }

                //별도의 스레드는 화면 변경작업 불가
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.tv.setText(" 문자 변경 완려");
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

        //서버에서 이미지 파일을  불러와서 이미지뷰에 보여주기
//
//        new Thread(){
//            @Override
//            public void run() {
//                String add = "http://webserver.dothome.co.kr/moana01.jpg";
//
//                try {
//                    URL url = new URL(add);
//
//                    InputStream is = url.openStream();
//
//                    Bitmap bm = BitmapFactory.decodeStream(is); // 스트림으로 읽어들인걸 비트맵으로 변환해주는 애
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.iv.setImageBitmap(bm);
//                        }
//                    });
//
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }.start();

// 위 작업이 우리가 그 동안 배운건데,
        // 서버 이미지를 쉽게 읽어와서 이미지뷰에 보여줄 수 있는 이미지로드 라이브러리
        //글라이드!
        String add = "http://webserver.dothome.co.kr/moana01.jpg";
        Glide.with(this).load(add).into(binding.iv);
        //헐 두줄에 끝남ㅋㅋ

    }



}