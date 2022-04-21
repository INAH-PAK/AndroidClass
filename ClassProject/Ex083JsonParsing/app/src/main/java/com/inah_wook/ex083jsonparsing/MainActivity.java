package com.inah_wook.ex083jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.inah_wook.ex083jsonparsing.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(view -> {

            // json object

            AssetManager manager  = getAssets();

            InputStream is = null;
            try {
                is = getAssets().open("mmm.json");
                int fileSize = is.available();

                StringBuffer buffer = new StringBuffer(fileSize);
                is.read();
                is.close();

                JSONObject jo = new JSONObject(buffer.toString());

                String name = jo.getString("name");
                String msg = jo.getString("msg");

                // 이렇게 뜯어옴옴
               binding.tv.setText(name + ", " + msg);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



        binding.btn2.setOnClickListener(view -> {

            //json array

            AssetManager manager = getAssets();

            // asset 폴더 안의 bbb.json4
            try {
                InputStream is = manager.open("jsons/bbb.json");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);
                StringBuffer buffer = new StringBuffer();

                while(true){
                    String line = reader.readLine();
                    if(line == null ) break;
                    buffer.append(line+"\n");

                }

                binding.tv.setText( buffer.toString());

                String s="";
                JSONArray array = new JSONArray(buffer.toString());
                for(int i = 0 ; i < array.length();i++) {
                    JSONObject jo = array.getJSONObject(i);

                    String name = jo.getString("name");
                    String age = jo.getString("age");

                    // 복합적인 제이슨!
                    // 객에 안에 객체가 있을 때,
                    //  이렇게 address 안에두가지 객체가 존재 할 때,
                    // address 에세 해당 객체를 뽑아옴.

                    // 날짜를 뽑아올 때 날씨 데이터를 json으로 파싱해서 가져와보자 !


                    JSONObject address = jo.getJSONObject("address");
                    JSONObject nation = address.getJSONObject("nation");
                    JSONObject city = address.getJSONObject("city");


                    s = "name : " +  name  + "age : "+ age + "\n";

                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        });

    }
}