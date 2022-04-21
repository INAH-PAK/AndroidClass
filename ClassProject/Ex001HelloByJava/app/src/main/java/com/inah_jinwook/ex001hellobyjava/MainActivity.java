package com.inah_jinwook.ex001hellobyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //화면에 보이는 뷰들의 참조변수는 가급적 맴버변수로 만들어라 !
    TextView tv;
    Button btn;
    LinearLayout layout;

    //Activity 생성되면 자동으로 실행되는 메소드 = 콜백 메소드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML로 쓰여진 함수를 지워보면 빈 화면의 앱이 실행 됨. ==> 내용물이 없음
        //setContentView(R.layout.activity_main);
        // 이제 화면을 JAVA 언어로 써보자 !

        //Activity에 놓여질 수 있는 것은 View 클래스를 상속받은 애들만 가능 (마치 Componant 처럼)

        //글씨를 보여주는 TextView 객체 생성 및 설정
        //앞으로 화면에 보여지는 애들의 참조변수는 꼭 전역으로 쓰자 !
        // 이 객체는 글자만 담고있는 객체 !
        tv = new TextView(this);  //괄호 안에 걍 뷰들은 this 쓰자.

        tv.setText("Hello world ! 인아의 화면 ");

        //이제 Activity (화면)에 객체를 붙이자 !
        //2 this.setContentView(tv);
        //이대로 실행시 글자가 나타난 것을 볼 수 있다 !


        //또 버튼 하나를 만들어 볼까?
        btn = new Button(this);
        btn.setText("Button");

        //이제 Activity (화면)에 객체를 붙이자 !
        // this.setContentView(btn); 근데 this.은 어차피 생략 가능하잖아?
        //2 setContentView(btn);
        //자바를 객체를 더할 땐, add . 안드로이드는 set 이잖아?
        // add는 원래 있던 것에 추가. set은 원래 있던 것을 바꿔치기 느낌 !
        //Activity 는 단 하나의 View만 놓여질 수 있다 !
        // 이제 실행해보면 텍스트 뷰는 안보이고 버튼만 보임. . . set이라서. . .

        //그럼 어떻게 여러개를 둠?
        //View들을 여러개 담을 수 있는 ViewGroup 객체를 1개 만들고,
        //거기에 View들을 붗이고
        // 그 ViewGroup 객체 1개를 Activity에 설정하기 !

        //ViewGroup class 들 중에서 가장 간단한
        //    [  LinearLayout ]  :  나중에 더 많이 배움.

        layout = new LinearLayout(this);  //투명한 판넬을 만들어땀 ~
        //defalt : 수평배치 .  설정 시 수직배치도 가능
        layout.addView(tv);
        layout.addView(btn);

        //이제 Activity에 ViewGroup 붙이기 !
        setContentView(layout);

        //자~ 실행시 글씨, 버튼 둘 다 나옴 !

        //자 이제 버튼 클릭하면 반응하여 TextView 글씨 변경하기 !
        //자바때는 액션 리스너 객체를 만들어서 감시시켰잖아?
        //안드로이드에서는 ?

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Hi");
            }
        });

        //근데 여기서 객체 만들고. .. 뷰 만들고. . . 하면 존나 복잡해지잖아
        //그래서 XML 객체를 만들어서 역할을 분리시킨거임 ~
        // 이렇게 객체를 분리해서 만들면 더 객체지향스럽 ~





    }
}