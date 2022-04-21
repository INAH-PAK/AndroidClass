package com.inah_wook.ex075alarm;

import android.app.AliasActivity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

//알람에 의해 실행될 녀석 ~
public class AlarmReceiver extends BroadcastReceiver {
// BroadcastReceiver : 방송하면 듣는 녀석
    // 방송이란? 부팅 다했어 ~ 배터리 없어 ~ 등 폰이 말하는 것들을 듣는 애들
    // 얘는 10초 이내에 끝나는 작업들만 하는 가벼운 알바녀석임~

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "알람을 들었어 ~ ", Toast.LENGTH_SHORT).show();
        //근데 4대 컴포넌트는 꼭 매니페스트에서 등록해야 함 ~
        //매니페스트 ㄱㄱ

        //근데 알람 울리면 토스트만 하진 않을 거 아냐?
        //예전에는 알람리시브 했을때 ( == 알람을 받았을 때,)
        //새로운 액티비티를 보여줬었음.
        // 새로운 액티비티 == 알람화면 ~

        //새로운 액티비티 만들기 ㄱㄱ

        //      자 ~~~~~~~~~
        //택배기사 고용 ~
        //Intent intent1 = new Intent(context,AlarmReceiver.class);

        // 예전 버전은 리시브 시에 곧바로 화면실행이 가능했었음.
        //근데 가장 최근 버전 Q 부터는
        // 이제는 무조건 알림 !! notification 으로 알림을 실행하도록 강제함.
        //이젠 이렇게 안 씀. ㅠ
        //  context.startActivity(intent1);
        //요즘 쓰는건 밑에 ㄱ

        // 알림 보이기: 40:00
        //존나 자주 쓰니까 연습 많이 하기~~
       //context.getSystemService(. . . .
        //NotificationManagerCompat 이렇게 쓰면 버전 상관없이 다 쓸 수있게 만들 수 있음.
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        NotificationChannelCompat channel = new NotificationChannelCompat.Builder("ch1",NotificationManagerCompat.IMPORTANCE_HIGH).setName("알람 채널").build();
        notificationManagerCompat.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"ch1");
        builder.setSmallIcon(R.drawable.ic_alarm); // 상태 아이콘
        builder.setContentTitle(" 나의 알람");
        builder.setContentText(" 밤이 되었습니다. 마피아는 조용히 고개를 들어주세요. ");
        // 액션버튼 3개 만들 수 있는데,
        // 다이얼알랏처럼
        //근데 지금은 그냥 확인만 만들어보자 ~
        Intent intent1 = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,10,intent1,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);
        builder.addAction(R.drawable.ic_alarm,"확인",pendingIntent);

        notificationManagerCompat.notify(11,builder.build());

        //근데 알람뜨고 확인 누르면 액티비티 화면이 뜨잖아?
        //근데 여기서 코드를 치면 인텐트가 또 발동하면서 액티비티가 또 생김 ~
        //근데 한 번 누르고 화면 꺼야 하니까,
        // 매니페스트 ㄱㄱ

        //android:launchMode="singleTop"
        // 이렇게 하면 화면 하나 있어? 그럼 나머지는 띄우지 마~ 하는고임 ~

    }
}
