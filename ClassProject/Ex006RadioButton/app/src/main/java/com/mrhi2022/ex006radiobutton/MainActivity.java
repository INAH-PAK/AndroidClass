package com.mrhi2022.ex006radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //화면에 보이는 뷰객체들의 참조변수들
    RadioGroup rg;
    Button btn;
    TextView tv;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰객체들을 찾아와서 참조변수에 대입
        rg= findViewById(R.id.rg);
        btn= findViewById(R.id.btn);
        tv= findViewById(R.id.tv);

        ratingBar= findViewById(R.id.rating);

        //버튼 클릭에 반응하는 리스너 객체 생성 및 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RadioGroup에게 체크된 RadioButton의 식별자(id) 알려달라고..
                int id= rg.getCheckedRadioButtonId();

                //id를 이용하여 해당하는 RadioButton객체를 찾아와서 참조하기
                RadioButton rb= findViewById(id);
                tv.setText( rb.getText().toString()  );
            }
        });

        //라디오버튼의 체크가 변경될때 마다 반응하려면 리스너를 일일이 설정해 줘야 하지만
        //번거로움. 라디오그룹에게 변경리스너를 새로이 생성하여 설정해주는 방법이 권장됨
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //두번째 파라미터 i : 체크된 라디오버튼의 id(식별자)가 전달됨

                RadioButton rb= findViewById(i);
                tv.setText( rb.getText().toString() );
            }
        });

        //레이팅값이 변경되는 것에 반응하는 리스너객체 생성 및 설정
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //두번째 파라미터 v : 선택된 rating 값 - 소수점 단위도 있음.
                //세번째 파라미터 b : 사용자가 터치를 이용해서 점수를 변경했는지 여부

                tv.setText( v +"점");

            }
        });

    }
}