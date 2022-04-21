package com.inah_wook.ex075alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        findViewById(R.id.btn2).setOnClickListener(v->{

            Intent intent = new Intent(this,AlarmService.class);
            stopService(intent);
            finish();



        });




    }
}