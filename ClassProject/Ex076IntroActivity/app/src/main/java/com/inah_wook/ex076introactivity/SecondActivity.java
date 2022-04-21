package com.inah_wook.ex076introactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {


    //이 액티비티는 앱 실행 후, 뒤로가기 버튼을 누르면 나오는 액티비티 임.
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    //디바이스의 뒤로가기 버튼 클릭시 반응하는 리스너 `


    @Override
    public void onBackPressed() {
       //  super.onBackPressed(); 이거 때문에 바로 꺼지는거임. 부모의 이메소드는 바로 꺼지도록 만들어져있음 이거 지우고 하고픈거 고
        new AlertDialog.Builder(this)
                .setMessage(" 진짜 나갈껴?  ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create();
    }
}