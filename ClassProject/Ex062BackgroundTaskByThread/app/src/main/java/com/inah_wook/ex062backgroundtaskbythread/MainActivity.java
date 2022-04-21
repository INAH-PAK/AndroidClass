package com.inah_wook.ex062backgroundtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> startTask());
        findViewById(R.id.btn_stop).setOnClickListener(view -> stopTask());





    }//온 create

            // 스탙 누르면 밑에 MyThread 실행 스찹 누르면 스탑 구현 함수들 ~
            MyThread myThread;
    
    // 또또 !
    // 안드로이드 12 (api 11) qjwjs qncjsms elqkfltmdml "back" 버튼을 클릭했을 때
    //액티비티가 finish( 액티비티가 꺼져서 메모리가 사라짐 ) 되지않고 그냥 홈화면에 가려만 짐.
    //걍 홈화면이 위에 덮어씌워진 느낌임.
    //다른  버전은 다 finish 됨.
    //근데 우리가 만들고 싶은 건 앱을 꺼도 !!! 백그라운드에서 돌아가고 싶잖아 !!
    //자, 액티비티의 종료란 앱이 꺼지는 것이고, 완전 백그라운드에서 없어지고 완전 메모리에서 변수들이 다 사라지는고얌.
    // 근데 우린 백그라운드에서 계속 돌아가게 하고 싶어.
    //그래서 이전버전들처럼 "back" 버튼을 눌렀을 때 액티비티가 완전히 finish() 되도록 조정하자 !
    //" 뒤로가기" 버튼클릭시에 자동으로 발동하는 메소드가 있오
    // 예를 들어, 앱 실행하면 뒤로가기 버튼 누르면 진짜 종료할꺼냐고 물어보는 애들 있잖아?
    //이걸로 하는고임


    @Override
    public void onBackPressed() {
        finish();
    }

    void startTask(){

        if(myThread != null) return;

        myThread = new MyThread();

        myThread.start(); // 이거 하면 자동으로 스레드의 런 메소드 실행됨
        return;

    }

    //service 컴포넌트를 사용하지 안고 백그라운드 작업을 했을 때의 문제점 알아보기
    //그러기 위핸 별도 스레드로 백그라운드 작업을 수행해보자.
    void stopTask(){

        if(myThread!= null){
            //myThread.stop(); 이거 하면 리소스가 남겨진 상태로 끝날 수 있기 때문에 자바에서 강제적으로 멈춤 . 절대 스레드를 직접 멈추면 안됑~
            //스레드의 종료는 런 메소드가 끝나면 자동으로 그탐 됨.
           // ..근데 우리는 while 문 째문에 런 메소드가 끝나지 않기 때문에 종료하려면 while 문을 빠져나와야 함 ~
                //즉, while문의 조건가뵤 isRun 을 false로 바꾸면 됨 ~

            myThread.isRun = false;

            // 자, 여기서 문제가 생김
            // 이 프로그램을 살펴보자.
            // 메인 프로그램을 실행시키면
            //먼저 MyThread 객체가 생성되며, 안엔 null값이 있어.
            //그리고 스타트 버튼을 누르면 run 메소드가 생성되서 토스트를 띄우는 객체가 생성됨,
            //이 객체를 t1 친구라고 하자?
            // 이제 앱을 종료하면 스레드는 계속 남아서 토스트를 띄울거야.
            //여기서 음 이건 녹음어플 듣고 정리하쟝 ㅠ
            //  2022.03.17 2:00:00


        }else{
            Toast.makeText(this, "Toast를 참조하고 있자 얺숩니당", Toast.LENGTH_SHORT).show();
        }


    }


    // main 이너클래스
    class MyThread extends Thread{
        boolean isRun = true;

        @Override
        public void run() {

            //5초마다 토스트를 보여주는 동작을 수행하는  동작수행 스레드 만들기 ~
            while (isRun){
                //별도의 스레드는 화면 변경 작업이 불가함.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MainActivity.this, " 얍 !", Toast.LENGTH_SHORT).show();
                    }
                });

                //한 번 출력하고 5초동안 대기
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}//main activity