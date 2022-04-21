package com.inah_wook.ex___http;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.inah_wook.ex___http.databinding.ActivityMainBinding;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGet.setOnClickListener(view -> clickGet());

        binding.btnPost.setOnClickListener(view -> clickPost());

    }



    private void clickGet() {
        new Thread() {
            // 먼저 서버에 보낼 데이터들 준비

            @Override
            public void run() {
                String title = binding.etTitle.getText().toString();
                String message = binding.etMsg.getText().toString();

                // 겟방식ㅇ로 보낼 서버의 주소
                String serverUrl = "http://webserver.co.kr/02RequestTest/getTest.php";

                //URL 에는 한글과 특수문자 사용 불가.
                // 그래서 한글을 URL 에 붙일 수 있도록 암호화 == 인코딩 함.


                try {
                    title = URLEncoder.encode(title, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                //겟 방식은 URL 뒤에 ? 붗이고 요청 파라미터 값들을전공
                String getUrl = serverUrl + "?title=" + title + "$msg=" + message;

                //서버와 연결

                URL url = null;
                try {
                    url = new URL(getUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                // 이미 겟 방식은 보낼 데이터를 URL뒤에 부였기 때문데
                //별도의 OutputStream 이 필요함
                //근데 우린 보내긴 했지만, 잘 갔는지 응답이 필요하잖아?

                // 서버(getTest.php)에서 응답된 글씨를 읽어오기 위해 inputStream도 필요함!
               InputStream is = url.openStream();


                StringBuffer buffer = new StringBuffer();


            }
        }.start();

    }

    private void clickPost() {
        new Thread(){

            String id =binding.etTitle.getText().toString();
            String pw =binding.etMsg.getText().toString();

            String serverUrl = "http://webserver.co.kr/02RequestTest/getTest.php";

            URL url;

            {
                try {
                    url = new URL(serverUrl);

                    //서버에 데이터를 보내기 위해 아웃풋 스트림 필요
                    //애석하게,,, url은 인풋 스트림만 열 수 있음.
                    // 우린 아웃풋 필요하잖오
                    // HTTP 통신 규약에 따라 데이터를 주고 받는 url 객체의 조수에 해당하는 역할 객체가 이뜸
                    //
                    HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                    // 통신 규약은 GET,POST 정해줘야 하잖아?
                    //이 커넥션에세 필수 속성 지정해줌
                    connection.setRequestMethod("POST"); // 무조건 대문자 유의~
                    connection.setDoInput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false); // 안드로이드가 정해놔땀
                    // 캐시 속성은 보안 등등 때문에 막아놓음

                    OutputStream o = connection.getOutputStream();

                    // 자바때의 printf() 명령을 쓸 수 있도록 하는 애
                    PrintWriter writer = new PrintWriter(os); // 보조문자스트림

                    //서버로 보낸 데이터들을 특정 포멧으로 만들기
                    String data= "id=" + id + "$pw=" + pw; //겟방식에서 물음표만 뺐다고 생각 ㄱ
                    writer.print(data);
                    writer.flush();
                    writer.close();

                    //서버로부터 응답되ㅓㄴ 결과 받기
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(is);

                    StringBuffer buffer = new StringBuffer();
                    while (true){

                        String line = reader.readLine();
                        if(line == null ) break;
                        buffer.append(line+"\n");

                    }
                    runOnUiThread(()-> binding.tv.setText(buffer.toString() );


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();


    }




}





