package com.inah_wook.ex064servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //서비스로 일시정지 기능 구현하는 법 ~

//A 뮤직 서비스 객체의 참조변수
    // 이렇게 참조변수를 이용해서 MusicService.java의 아예 기능 메소드들을 실행하는고임 !
    // 아까의 예제에서는 직접 스타트 스탑 이렇게 했잖아 ~
    // 근데 서비스를 실행하는 것은 내가 new 를 하면 안되고 ㅇintent 를 해야 한다고 했잖아?

    // B . onCreate 메소드 중간에 ㄱㄱ


    MusicService musicService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_start).setOnClickListener(view -> playMusic());
        findViewById(R.id.btn_pause).setOnClickListener(view -> pauseMusic());
        findViewById(R.id.btn_stop).setOnClickListener(view -> stopMusic());


    }


//B 액티비티가 화면에 보일 때 서비스 객체를 만들고 연결 ㄱㄱ
    @Override
    protected void onResume() {
        super.onResume();

        if(musicService == null ){
            // MusicService musicService; 이딴 코딩 절대 하면 안돼 !!! 무조건 인텐트 !  03:40:00
            Intent intent = new Intent(this,MusicService.class);
            startService(intent);// 서비스 객체가 없으면 서비스 객체 생성 후, onStartCommend() 가 호출 됨
            // 근ㄷ 만약 객체 없으면 그냥 바로 onStartCommed() 발동함.


            //자 그럼 위의 명령으로 만들어진 musicService 객체와 연결하자 ~
            //매개변수 설명 : 1. 누구랑 연결해? 아까 만든 인텐트 ~
            //              2.( C ) 메인 엑티비티.java 와 MusicService.java 와 연결할꼰데
            //              인텐트가 새로 객체를 만든거라 인텐트 아조씨가 MusicService.java로 만든musicService 객체가 오딨는지 알잖아?
            //              자, 인텐트 아조씨보고 연결해달라고 하면 갑자기 관뚫음
            //              바인드 한다고 하는데, 메인이랑 뮤직서비스 에 갑자기 씽크홀 만듬
            //               이 씽크홀에는 직원 하나가 있는데 이 직원만 여길 드나들 수 있음 == binder
            //            자, MusicService.java 로 가서 onBind() 를 보자.

            //정리하면 메인에서 bindService() 명령으로 인텐트에세 서비스가 누군지 물어봄.
            // 그럼 conn이라는

            bindService(intent,conn,0); // 1. 야 인텐티야 주소 궁금하니까 가서 2. 씽크홀 직원 데려와 ~ 3.4:07:00 참고


        }



    }


//여기서 당장 미디어 플레이어를 제어할 순 없으니까 위에서 뮤직 플레이어 참조변수를 만들자 ~ A

//bindService() 로 서비스 객체와 연결하는 통로객체
    //맴버변수 위치
    ServiceConnection conn = new ServiceConnection() {
        @Override //연결되면 ~이고실행 ~
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //두번째 파라미터 iBinder : 서비스 객체에서 넘어온 씽크홀 직원 ~ 얘가 서비스객체의 주소값을 앎.
            MusicService.MyBinder myBinder = (MusicService.MyBinder) iBinder;
            musicService = ((MusicService.MyBinder) iBinder).getMusicServiceAddress();

            Toast.makeText(musicService, "드뎌 메인과 뮤직서비스가 연결되어따 ~ ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {



        }
    };



    void playMusic(){

   if(musicService != null) musicService.playMusic();



    }

    void pauseMusic(){
 if(musicService!=null) musicService.pauseMusic();


    }
    void  stopMusic(){

        if(musicService!=null){
            musicService.stopMusic();

            //이때 아까 intent 가 만들어 둔 씽크홀 없에자 ~
            unbindService(conn);
            musicService = null;

        }
Intent intent = new Intent(this,MusicService.class);
        stopService(intent); // 인텐트야 서비스 죽여라 ~
        finish(); //액티비티도 종료 ~

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}