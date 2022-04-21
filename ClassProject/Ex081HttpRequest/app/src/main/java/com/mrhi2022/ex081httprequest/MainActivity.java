package com.mrhi2022.ex081httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrhi2022.ex081httprequest.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGet.setOnClickListener(v->clickGet());
        binding.btnPost.setOnClickListener(v->clickPost());
    }

    void clickGet(){
        //네트워크 작업은 반드시 별도 Thread 가 해야함
        new Thread(){
            @Override
            public void run() {

                //서버에 보낼 데이터들
                String title= binding.etTitle.getText().toString();
                String message= binding.etMsg.getText().toString();

                //GET방식으로 보낼 서버의 주소(URL)
                String serverUrl="http://mrhi2022.dothome.co.kr/03AndroidHttpRequest/getTest.php";

                //URL에는 한글 및 특수문자 사용 불가 - 한글을 URL에 사용할 수 있도록 암호화[인코딩]
                try {
                    title= URLEncoder.encode(title,"utf-8");
                    message= URLEncoder.encode(message,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //get방식은 URL 뒤에 ?를 붙이고 요청파라미터 값들(title,message)을 전송
                String getUrl= serverUrl + "?title="+title+"&msg="+message;

                //서버와 연결
                try {
                    URL url= new URL(getUrl);

                    //이미 GET방식은 보낼 데이터를 URL뒤에 붙였기에..
                    //별도의 OutputStream은 없어도 됨.

                    //서버(getTest.php)에서 응답된(echo된) 글씨를 읽어오기 위해 InputStream 필요
                    InputStream is= url.openStream();
                    InputStreamReader isr= new InputStreamReader(is);
                    BufferedReader reader= new BufferedReader(isr);

                    StringBuffer buffer= new StringBuffer();
                    while (true){
                        String line= reader.readLine();
                        if(line==null) break;

                        buffer.append(line+"\n");
                    }

                    //별도 Thread는 UI 작업 불가
                    runOnUiThread(()->{ //람다식으로 축소
                        binding.tv.setText(buffer.toString());
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    void clickPost(){
        new Thread(){
            @Override
            public void run() {
                //서버로 보낼 데이터들
                String id= binding.etTitle.getText().toString();
                String pw= binding.etMsg.getText().toString();

                //POST방식으로 보낼 서버 주소
                String serverUrl="http://mrhi2022.dothome.co.kr/03AndroidHttpRequest/postTest.php";

                try {
                    URL url= new URL(serverUrl);
                    //URL은 InputStream만 열수 있음.

                    //HTTP 통신 규약에 따라 데이터를 주고받는 역할을 수행하는
                    //URL객체의 조수역할을 하는 객체가 있음.
                    HttpURLConnection connection= (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");//반드시 대문자
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    //서버에 데이를 보내기 위해 OutputStream이 필요
                    OutputStream os= connection.getOutputStream();
                    PrintWriter writer= new PrintWriter(os); //보조문자스트림

                    //서버로 보낼 데이터들을 특정 포멧을 만들기
                    String data= "id="+id+"&pw="+pw;
                    writer.print(data);
                    writer.flush();
                    writer.close();

                    //서버(postTest.php)로부터 응답된 결과 받기
                    InputStream is= connection.getInputStream();
                    InputStreamReader isr= new InputStreamReader(is);
                    BufferedReader reader= new BufferedReader(isr);

                    StringBuffer buffer= new StringBuffer();
                    while (true){
                        String line= reader.readLine();
                        if(line==null) break;

                        buffer.append(line+"\n");
                    }

                    runOnUiThread( ()->binding.tv.setText(buffer.toString()) );


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}