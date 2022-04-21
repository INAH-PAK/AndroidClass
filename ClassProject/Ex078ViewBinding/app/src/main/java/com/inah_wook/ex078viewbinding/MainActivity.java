package com.inah_wook.ex078viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.inah_wook.ex078viewbinding.databinding.ActivityMainBinding;

// findViewById 메소드의 번거로움과 서능문제를 해경한
//안드로이등의 기능임 != 라이브러리가 아님
// 이 기능을 on 하러 -> build.gradle ㄱㄱ
// 단, 이 기능은 자동완성이 안되므로 외워놓자 !

// viewBinding 기술의 특징 / 원리
//xml 레이아웃 파일(activity_main.xml) 의 뷰들을 이미 연결하고 있는
//클래스가 자동으로 만들어져 있음.
//이 클래스의 이름은 xml 문서의 이름을 기반으로 만들어져 있음.

// 포트폴리브에
// 2:30:00 꼭 적기


public class MainActivity extends AppCompatActivity {

    // 표기법 : activity_main.xml파일의 이름을 이렇게 낙타표기로 쓰고 끝에 바인딩 이라고 쓰면 됨~
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        // 그 동안은 우리가 이렇게 썼잖아 ? 엑티비티한테 찾으라고 해서 내가 맨날 파인드뷰바이~ 했던거임~
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // 그럼 실제로 얘는 activity_main.xml 이 문서를 기반으로 뷰 객체들을 생성(inflate)하여 바인딩 객체 생성한거임 ~

        //이제 액티비티에게 보여줄 내용물 뷰 ( ContentView ) 를 binding 객체의 뷰로 설정

        setContentView(binding.getRoot()); // ->  얘가 가져오는건 맨 위의 리니어 레이아웃을 만들거임.
        //이제 다 끝난거임 !
        // 바인딩 객체안의 id 가 tv의 글씨르르 변경해보겠다'

        //이미 바인딩 되어있기 때문에 별도의 파인드바이뷰아이디 나 텍스트뷰 참조변수가 필요 없음
        //바인딩 객체안에 아이디 기반으로 각 뷰와 연결된 참조변수들이 이미 만들어져 있음~
        // 이젠 아이디가 변수명 이름인 !!!!!

        binding.tv.setText("dd");
        binding.btn.setOnClickListener(view -> binding.tv.setText("버튼 누르니까 바뀜"));

        // 롱 클릭 리스너를 람다식으로 변경도 가능 ~
        // 원래의 정석 방식
//
//        binding.btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                return false; //이게 모냐면 ~ 버튼을 꾸우욱 누르면 롱 클릭 발동 -> 눌렀다떼면 클릭 리스너가 발동 하게 되자나? 그걸 허용 == ture. 아님 false
//            }
//        });

        // 내가 아는 람다식 ~ v 는 원래 식에 있던 매개변수 값임 ~ 두개면 걍 콤마로 찍음 돼~
        binding.btn.setOnLongClickListener(v->{
            Toast.makeText(this, "롱 클릭 했구나?", Toast.LENGTH_SHORT).show();
            return true;
        });

        binding.btn2.setOnClickListener(v->{
            binding.tv2.setText(binding.et.toString());
            binding.et.setText("");
        });

        // 동적으로 MyFragment 붙이기
        //TODO 놓침 ㅜ
        MyFragment myFragment = new MyFragment();
        // 프레그 안의 프레그때는 차일드 프레그먼트 퍼포트 ㅐ니저
    //    getSupportFragmentManager().beginTransaction().add()



        )



    }

    @Override
    protected void onResume() {
        super.onResume();

        item




    }

    }