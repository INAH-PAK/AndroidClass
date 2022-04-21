package com.inah_jinwook.ex014edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //1. 화면에 보여지는 뷰들을 참조하는 참조변수부터 만들자 !
    EditText et01;
    EditText et02;
    EditText et03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰 객체들을 찾아와서 참조변수에 대입
        et01 = findViewById(R.id.et01);
        et01 = findViewById(R.id.et02);
        et01 = findViewById(R.id.et03);

        //editText 가 변경될 때 마다 리스터 객체를 생성 및 추가 !

        et01.addTextChangedListener(new TextWatcher() {
            @Override
            //글자가 바뀌기 전의 글자를 알 고 싶을 때
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            //글자가 바뀔 때 (우린 이게 중요 ! 거의 다 이거 씀)
            //charSequence 는 string의 부모 ~ 걍 알고만 있어라 ~
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if( charSequence.length() >=3 ){
                    //커서를 EditText 2번으로 이동 ( == et02 이 포커스를 가진다는 의미임  )
                    //포커스란? 현재 실행되는 녀석이 포커스를 가진거임,
                    //그럼 우린 이제 포커스를 2번이 요청하게 시키면 됨.
                    et02.requestFocus();
                }

            }

            @Override
            //글자가 바뀌고 난 후
            public void afterTextChanged(Editable editable) {

            }
        });

        et02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if( charSequence.length() >=4) et03.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });











    }
}