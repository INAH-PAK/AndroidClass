package com.inah_wook.ex051threadprogressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    ProgressDialog dialog;
    int gauge = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //먼저 휠
        findViewById(R.id.btn1).setOnClickListener( view -> {

            if(dialog!=null) return; //아무작업 안함.
            dialog = new ProgressDialog(this);
            dialog.setTitle("wheel Dialog");
            dialog.setMessage(" 다운로드 하는 중 , , , ");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCanceledOnTouchOutside(false); //다이알로그 중 다른 곳 눌러도 안꺼짐
            dialog.show();

            //원래는 네트워크 작업시, 코드가 이 곳에 작성되어야하지만,  지금은 테스트 목적이므로 삼초정도후에 다이얼라그가 종료되도록해보게땀.
            //3초 뒤에 dialog 종료(dismiss)하기 위해
          handler.sendEmptyMessageDelayed(0,300);



        });





        findViewById(R.id.btn2).setOnClickListener( view -> {
            if (dialog !=null) return;

            dialog = new ProgressDialog(this);
            dialog.setTitle(" 막대바 다운로드" );
            dialog.setMessage(" 다운로드 하는 중 , , , ");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCanceledOnTouchOutside(false); //다이알로그 중 다른 곳 눌러도 안꺼짐

            dialog.setMax(10);
            dialog.show();

            //게이지값 설정
            dialog.setProgress(gauge);

            //gauge를 증가시키는 별도의 스레드
            //실무에서는 네트워크 자ㅏㄱ업임
            new Thread(){
                @Override
                public void run() {


                    while(gauge<100){

                        gauge++;
                        dialog.setProgress(gauge);
                    }

                    dialog.dismiss();
                    dialog.show();
                }
            }.start();

        });





    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //sandMessage()를 통해 전달되 ㄴ메세지가 처리될 때
            //sanEmptyDelay
            dialog.dismiss();
            dialog=null;
        }
    };
}