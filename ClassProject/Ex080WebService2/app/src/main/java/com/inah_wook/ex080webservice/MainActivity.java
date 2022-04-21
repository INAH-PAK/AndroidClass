package com.inah_wook.ex080webservice;

import androidx.appcompat.app.AppCompatActivity;

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

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn01.setOnClickListener(view -> loadTextFile());
        binding.btn02.setOnClickListener(view -> loadImageFile());
    }

    private void loadTextFile() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String server = "http://webserver.dothome.co.kr/index.html";

                try {
                    URL url = new URL(server);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();

                    while (true){
                        String line = reader.readLine();
                        if(line == null) break;

                        buffer.append(line+"\n");
                    }

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


            });
        t.start();



    } // load text file


    private void loadImageFile() {
        //서버에서 이미지 불러오기
        String add = "http://webserver.dothome.co.kr/moana01.jpg";
        Glide.with(this).load(add).into(binding.iv);

    }


}