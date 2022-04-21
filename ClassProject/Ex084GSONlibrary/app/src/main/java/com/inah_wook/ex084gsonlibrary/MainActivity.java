package com.inah_wook.ex084gsonlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inah_wook.ex084gsonlibrary.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn01.setOnClickListener(v->{
            // 원래 기본으로 존재하는 제이슨 파싱 클래스를 먼저 복습하자.
            // 원래는 파일로 만들었지만,
            // 자ㅡ
            // 제이슨은 그저 표기 형식이야. 글씨들인데 나름의 규칙이 있어
            // 그래서 그냥 스트링으로 하나 가지고 있어보자. 이걸로 걍 복습
            String jsonStr = "{'name':'Sam','age':24}";
            //원래 이 객체를 하나

            //name, age를 가진 person 객체로 만들어서
            // 파싱 한 값을 대입.
            // Json 이 { 시작 -> JsonObject
            //Json 이 [시작 -> JsonArray

//            JsonObject jo= new JsonObject(jsonStr);
//            String name = jo.getAsString("name");
//            int age = jo.getAsInt("age");
//            Person p = new Person(name,age);
//            binding.tv01.setText(p.name + ", "+ p.age);
//
//

        });
        binding.btn02.setOnClickListener(v->{
            // GSON 라이브러리 이용
//            .. json 문자열을 곧바로 person 객체로 만들어 줌
//                    원래는 일일히 원하는 데이터들을 하나하나 불러와야 하는데,
//
            String jsonStr = "{'name':'Sam','age':24}"; // 가져올 서버에 있던 값이라고생각하고 ~
            Gson gson = new Gson();
            // 두번째 파라미터 : 클래스의 정보를 줘야 함
            // 그럼 알아서 클래스의 설계 도면을 보고 알아서 gson이 안의 내용을 줌~
            Person person= gson.fromJson(jsonStr,Person.class);

            binding.tv01.setText(person.name + ","+person.age);


        });


        binding.btn03.setOnClickListener(view -> {
            String jsonStr = "{'name':'Sam','age':24}";
            Person person = new Person("hong",87);
            Gson gson = new Gson();

            String s = gson.toJson(person);
            binding.tv01.setText(s);
            //객체를 파일로 저장하는 등의 작업시에 용이함.
            // 그러니까, 아이템들을 저장해서 제이슨을 문자열로 저장해서 통채로 저장하니까
            // 훨씬 쉽고, 서버로 보낼 데이터가 많을 때,

        });

        binding.btn04.setOnClickListener(view->{

            // 인터넷 상에 있는 json 파일을 읽어서 파싱하기!!!!!!!
            // 비주얼 스튜디오로 Test.json 만드러서 내 닷홈 http://webserver.dothome.co.kr/Test.json 에 파일질라로 올리기~

            new Thread(){
                @Override
                public void run() {
                    String server ="http://webserver.dothome.co.kr/Test.json";
                    try {
                        URL url = new URL(server);

                        InputStream is = url.openStream();
                        InputStreamReader isr = new InputStreamReader(is);

                        //BufferedReader bufferedReader = new BufferedReader(isr);
                        //헐? 여기서 이거 쓰고 반복문으로 한 줄씩 존나 돌렸는데
                        //Gson 은 그 작업을 대신 해줌
                        Gson gson = new Gson();
                        Person person = gson.fromJson(isr,Person.class);

                        //별도 스레드는 화면에 그림 못그림~

                         runOnUiThread(()->{
                             binding.tv01.setText(person.name + "," + person.age);
                         });



                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }.start();

            binding.btn05.setOnClickListener(v->{
                //jonArray

                String jsonStr = "[{'name':'Sam','age':24}],[{'name':'ianh','age':27}]";

                //


                Gson gson = new Gson();
                Person[] people = gson.fromJson(jsonStr,Person[].class);
                StringBuffer buffer = new StringBuffer();
                for(Person p : people){
                    buffer.append(p.name + "," + p.age+"\n");
                }
                //
             //   ArrayAdapter<Person> list = (ArrayList<Person>)Arrays.asList(person);

                binding.tv01.setText(buffer);
            });
            // 근데 HTTP 할 때 마다 스레드 인풋스트림 계!!속 만들었는데
            // 이거 이제 안쓰고 라이브러리로 쓰자,,,, 왜 지금 알려줘ㅠㅠㅠㅠ

            // 1.OkHTTP
            // 2. Retrofit ( OkHTTP 의 향상된 버전 ) ->  현재는 Retrofit2 아예 업글
            // 3. Volley (현재는 구글이 인수 -> 근데 이제 업글 안쓴다고 했다가 이번에 했긴 했는데 불안해서 안쓰기 ㅋㅋ)
            // 우리는 오늘 RetroPit2를 주요 목적으로 사용할거임!!!!!
            //









        });



    }
}