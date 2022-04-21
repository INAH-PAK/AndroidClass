package com.inah_wook.ex064servicebind;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

//안드로이드 4대 컴포넌트는 andoid manifests .xml 에 등록
public class MusicService extends Service {

    MediaPlayer mp;
    // 근데 얘느 ㄴ


    @Override //서비스 객체가 처음 생성시 발동되는 콜백메소드 ~
    public void onCreate() {
        Toast.makeText(this, " 객체 생성됨 ~ ", Toast.LENGTH_SHORT).show();

        super.onCreate();
    }

    @Override //시작시 ~ 자동호출 콜백메소드 ~
    // 이걸 여러번 했다고 해서 여러개의 객체가 만들어지지 않고
    //갹체가 없으면 자동 생성 후 이거 발동하고, 있었으면 바로 이 메소드가 실행됨
    //결국 스타트 명령이 실행된다면 이게 된다는 얘기얌 ~
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " onStartCommand 이 메소드 실행 ~ ", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override // 종료시 ~~
    public void onDestroy() {
        super.onDestroy();
    }


    // C. bindService() 명령으로 서비스와 액티비티가 연결되면 자동으로 발동하는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 자 요기서 메인에게 보낼 씽크홀 직원으로 보내자 ~
        //밑에서 만들기 ~
        return new MyBinder();
    }

        //Main 액티비티의 conn 에 넘어갈 객체 만들기
    class MyBinder extends Binder{
        // 이 서비스 객체의 주소를 리턴하는 주소 메소드 ~
            public MusicService getMusicServiceAddress(){
                return MusicService.this;
            }
        }






    // MediaPlayer 객체 제어용 메소드 3개

    public void playMusic(){
        if(mp==null){
            mp = MediaPlayer.create(this,R.raw.kalimba);
            mp.setVolume(0.7F,0.7F);
            mp.setLooping(true);
        }
            if(!mp.isPlaying()) mp.start(); //플레이하는 중 아니면 플레이 해 ~
    }

    public void pauseMusic(){

            if(mp!=null&& mp.isPlaying())mp.pause();

    }
    public void  stopMusic(){
            if(mp!=null){
                mp.stop();
                mp.release();
                mp=null;
            }



    }




}
