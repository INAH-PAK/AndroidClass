package com.inah_wook.ex075alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
// 이거 알람 끄면 다 사라짐.
    // 부팅완료되면 파일 입출력으로 저장된 걸 꺼내와라~ 해야 함,
    //나중에 한달동안 어플 만들 때 구현 ㄱㄱ. . .
    //A
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSystemService :  3/23 : 17:00
        //A

                //다시 아까 밑으로 ㄱㄱ A

                findViewById(R.id.btn).setOnClickListener(view -> {

            //10초 후에 알람 울리도록 해보자

            //알람 울리고 뭘 할거냐?
            // 여기서 뭘 하다 == 4대 컴포넌트로 구현한다.
            // 앱티비티, 서비스 , 브로그캐스트 리시버 설정가능
            // 단 ! 이젠 버전업이 되면서,
            // 이제는 알람에서 곧바로 액티비티 실행을 제한한다.
            // 특정 시간이 되면 자동으로 화면이 뿅 뜨게 ~

            // 그럼 몰 할거야 지금?
            // 알람에 의해 실행 될 리시버를 만들자 !
            //그럼 먼저 리시버를 실행시켜 줄 인텐트 만들기 ~
            // 앱티비티, 서비스 , 브로그캐스트 리시버 를 한다 ==> 무조건 인텐트 하나 만든다.

            Intent intent = new Intent(this, AlarmReceiver.class);

            // 매니페스트 갔다왔어?>
            //이 인텐트 객체가 알람리시버를 찾아서 오는 애임~
            // 인텐트는 존나 바쁜 택배 기사님임
            // 그래서 인텐트는 만들면 무조건 바로 일 함.
            // 근데 나는 알람에 숨겼다가 나중에 일하게 예약하는건 어케 함?
            // 인텐트를 보류시켜야 함.

            // 알람에 의해 잠시 보류되어 있어야 하는 인텐트로 만들기 ~
            //flag : FLAG_MUTABLE == 변화할 수 있는걸 줘야 함. 31버전부터 달라짐.
            PendingIntent panddingIntent = PendingIntent.getBroadcast(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);

            //이제 10초 후에 발동하는 알람 설정 ㄱㄱ~
            // 알람을 관리하는 알람 매니저 필요.
            //아예 맴버변수로 만들자~ 여러군데 쓸 수 있응께 `
                    //onCreate 위쪽으로 ㄱㄱ  A

                    //setExactAndAllowWhileIdle : 버전업 되면서 무조건 이거 써야 함, 그냥 setExact() 쓰면 에러.
                    //M 마시멜로우 버전부터 Doz (낮잠) 모드가 생겨서 이를 깨우고 알람을 울리도록. . .
                    //이건 폰의 배터리 효율성때문에 생겼는데
                    //만약 화면이 꺼지잖아?
                    // 백그라운드에서 돌아가는 앱들이 너무 많아서 메세지 수신 등, 꼭 필요한 앱들만 놔두고 잠재워놔야 함.
                    // 그러고 화면이 켜지면 애들 깨워야 ㅎㅁ

                    //ELAPSED : 폰이 부팅된 시간을 가지고 기준을 설정해라. -> 어차피 동작 안함
                    // RTC : 세계 표준시를 기준으로 설정해라.
                    // 근데 어차피 버전업 되면서 RTC 만 사용 가능.

                    //1970년 0시 0분 0초부터 시작된 카운트가
                    // 과거 시간으로 알람을 잡으면 그냥 울려버림
                    //만약 1000으로 잡으면
                    // 1970년 1월1일 1초로 잡은거임.
                    // 개극혐이잖아..
                    // 칼랜더, 등등 있는데
                    // System.currentTimeMillis()*10000 이거쓰기 : 현재시간을 가져오고 +10초 뒤에 실행해라.
                    // 27:20
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+10000,panddingIntent);

            //매니페스트 ㄱㄱ
                    // C   <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM"/>

                    //근데 알람 울리면 토스트만 하진 않을 거 아냐?
                    //알람리시버 자바 ㄱㄱ


        });
//  ======================================예제 2
findViewById(R.id.btn2).setOnClickListener(v->{
    // 특정 날짜와 시간을 선택하는 다이얼로그 보이기
    // 먼저 날짜를 선택하는 다이얼로그

    Calendar calendar = Calendar.getInstance(); // 이 명령으 ㄹ내리는 이 순간 시간 가져옴
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DATE);


    DatePickerDialog dialog = new DatePickerDialog(this,dateSetListener,year,month,day);
    dialog.show();
});
    } //on 크리에이트

    //특정 날짜와 시간을 저장하는 맴버변수
    //DatePickerDialog(); 3번째 이후 값 만들때 필요.
    int year , month , day ;
    int hour , min ;

    //날짜 선택을 듣는 리스너
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            //요기 파라미터 int i, int i1, int i2  -> 선택한 년, 월, 일
            year = i;
            month = i1;
            day= i2;

            //시간선택 다이얼로그 보이기
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);

            new TimePickerDialog(MainActivity.this,timeSetListener,hour,min,true ).show();


        }
    };
   // TimePickerDialog( ) 한테 필요한 시간선택 완료 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
       @Override
       public void onTimeSet(TimePicker timePicker, int i, int i1) {
           hour = i;
           min = i1;

           // 다 하고 왔어?
           //이제 선택한 날짜와 시간으로 알람 설정 하려면?
           Calendar calendar =Calendar.getInstance(); // 이렇게 하면 이걸 만든 딱 지금 현재 시간으로 생성되니까
           calendar.set(year,month,day,hour,min,0);     //이렇게 우리가 시간 셋팅 가능

           //알람 설정을 실행할 컴포넌트
           //리시버는 짦은 것만 하니까
           // 긴 작업을 할 서비스를 만들오보쟝 ~
           Intent intent = new Intent(MainActivity.this,AlarmService.class);
           // 작업 보류하려면 PendingIntent 만들기 ㄱ~
           // getForegroundService() 는 마시멜로우 버전 이상에서만 돌아가고 그 전에껀 기본으로 해야함
           //걍 써라운드 ㄱ
           PendingIntent pendingIntent;
           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                pendingIntent = PendingIntent.getForegroundService(MainActivity.this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);
           }else{
               pendingIntent = PendingIntent.getService(MainActivity.this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE)
         ;
           }






           //이렇게 쓰고
           //이제 알람 설정해보자
           alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,10000,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE);




       }


   };




}