package com.mrhi2022.ex083jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.mrhi2022.ex083jsonparsing.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v->{
            //json object parsing

            //assets폴더의 파일을 가져오기 위해 assets폴더 창고관리자 소환
            AssetManager assetManager= getAssets();

            //assets/aaa.json 문서의 파일을 읽기위한 InputStream
            try {
                InputStream is= assetManager.open("aaa.json");
                InputStreamReader isr= new InputStreamReader(is);
                BufferedReader reader= new BufferedReader(isr);

                StringBuffer buffer= new StringBuffer();
                while (true){
                    String line= reader.readLine();
                    if(line==null) break;
                    buffer.append(line+"\n");
                }

                //읽어온 json형식의 문자열을 확인
                //binding.tv.setText(buffer.toString());

                //json문자열을 분석하기
                JSONObject jo= new JSONObject(buffer.toString());
                String name= jo.getString("name");
                String message= jo.getString("msg");

                binding.tv.setText(name+"\n"+message);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });

        binding.btn2.setOnClickListener(v->{
            //json array parsing

            AssetManager assetManager= getAssets();
            //assets/jsons/bbb.json
            try {
                InputStream is= assetManager.open("jsons/bbb.json");
                InputStreamReader isr= new InputStreamReader(is);
                BufferedReader reader= new BufferedReader(isr);

                StringBuffer buffer= new StringBuffer();
                while (true){
                    String line= reader.readLine();
                    if(line==null) break;
                    buffer.append(line+"\n");
                }

                binding.tv.setText(buffer.toString());

                //json array parsing
                JSONArray jsonArray= new JSONArray(buffer.toString());

                String s="";
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jo= jsonArray.getJSONObject(i);

                    String name= jo.getString("name");
                    int age= jo.getInt("age");

                    JSONObject address= jo.getJSONObject("address");
                    String nation= address.getString("nation");
                    String city= address.getString("city");

                    s += name+" : "+age+"-"+nation+","+city+"\n";
                }

                binding.tv.setText(s);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
    }
}