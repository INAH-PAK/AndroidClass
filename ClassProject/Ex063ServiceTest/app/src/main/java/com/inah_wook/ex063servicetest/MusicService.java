package com.inah_wook.ex063servicetest;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

//안드로이드 4대 컴포넌트는 반드시 manifast에 등록해야 함  ㄱㄱ
//그리고 컴터 입장에선 컴포넌트는 다 개별 앱이라고 생각하고 각각 운영체제 능력을 가지고 있어서 좋음 ~
public class MusicService extends Service {

    //백그라운드에서 살아있는 녀석
    MediaPlayer mp;


    //startMusic() 에서 startService 명령으로 이 서비스 객체가 생성되어 시작되면 자동으로 발동되는
    //라이프사이클 콜백 메소드가 존재 !
    //이고
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //미디어 플레이어 생성하여 실행하자.
        if(mp ==null){
            mp = MediaPlayer.create(this,R.raw.kalimba); //mp3에 씨디넣고 ~
            mp.setVolume(0.7F,0.7F ); //소리 조절해주고 ~ ( 3D 같은 효과도 낼 수 있음 ~ )
            mp.setLooping(true);// 너ㅗ래 ㄲㅌ나면 다시 재생할래? 웅~
            //pause() // 이건 일시정지 ~

        }

        // 보통  시작 or 이어하기 버튼이 같이 있음 ~
        mp.start();


        // 이 메모리 프로세스가 사용자가 원하지도 않아쓴데프롯스가 강제로 kill
        //이때 다시 메모리 문자가 해결되면 자동으로 실핼도ㅣ는 메소드
        // 2:41:00
        return START_STICKY;
    }
    // stopService() 랑 startForegroundService() 명령으로 이 서비스 객체가 자동으로
    //발동하는 라이프사이클 콜백 메소드가 존재함.
    //그니까 죽을때 실행되는 메소드
    @Override
    public void onDestroy() {

        //startForegroundService() 로 실행 서비스는 반드시 이 메소드를 호출하여
        //forerground service 로 동작함

        //2:54:00
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("ch1", "채널에 보여지는 글씨 ~",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this,"ch1");
        }else{
            builder = new NotificationCompat.Builder(this,"");

        }

        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setContentTitle(" music service");
        builder.setContentText(" 뮤직 서비스 실행 ~");

        // 알림창을 클릭했을 때 뮤직서비스를 종료하는 버튼을 가진 메인 엑티비티 실행
        //하도록 ~~ 보류중인 intent 제작
        //펜딩 인텐트 ~
        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,100,i,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        startForeground(100,notification);



        //1. 이걸 쓰려는데 엥 필수로 노티피케이션을 달라네? 안드로이드가 개발자를 강제하는 방법 ㅜㅜ


        //서비스 끄기 전에 미디어 플레이어를 종료~
        if(mp!=null){

            mp.start();

            mp.release();
            mp.stop();


        }

        stopForeground(true);



        super.onDestroy();
    }

    //이건 다음 예제에서 함
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
