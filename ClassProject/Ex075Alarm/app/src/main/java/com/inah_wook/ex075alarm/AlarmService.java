package com.inah_wook.ex075alarm;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.security.Provider;
import java.util.List;
import java.util.Map;

public class AlarmService extends Service {

    // 야 첨왔냐
    //중간에 B 로 가.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //서비스는 액티비티를 상속받아 만들었다!
        // 그래서 context 를 this라 써도 됨.
        //A
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        NotificationChannelCompat channel = new NotificationChannelCompat.Builder("ch1",NotificationManagerCompat.IMPORTANCE_HIGH).setName("알람 채널").build();
        notificationManagerCompat.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"ch1");

        builder.setSmallIcon(R.drawable.ic_alarm);
        builder.setContentText(" 알람2");
        builder.setContentTitle(" 알람 투투투투투투~ ");

        Intent intent1 = new Intent(this, AlarmService.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,10,intent1,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);
        builder.addAction(R.drawable.ic_alarm,"확인",pendingIntent);

        notificationManagerCompat.notify(11,builder.build());

        //B
        // 자, 먼저
        // 포어그라운드로 동작한다는 기능 호출이 있어야 함.
        //포어그라운드 시작해 ~
        //A : 근데

        startForeground(12, builder.build()); // 얘가 알아서 해줌


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
                // 포어그라운드 쓰려면 퍼미션 필요 ~
        //매니페스트 ㄱ
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
