package com.inah_wook.ex079databainding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.inah_wook.ex079databainding.databinding.ActivityMainBinding;
// 우리가 xml, java 에서 코드를 쓰는데,
// 프레그먼트 할 때 각각의...
// 아예 그냥 뷰와 데이터를 연결한 데이터 바인딩

//우린 그 동안 참조변수 만들어서 값 바뀔 때 마다 셋텍스트 셋이미지 리소스 이런식으로 했잖아?
// 근데 이건 복잡하니까
//우선은 우리는 뷰바인딩으로 하쟝
// MVVM 등의 파일 관리 방식? 꼭 공부해가자.
// 녹음파일 1시간 후반 ㄱ

public class MainActivity extends AppCompatActivity {

    //데이터 바인딩은 뷰바인딩의 업그레이드 버전( 데이터 바인딩을 하면 뷰 바인딩은 ㅈㅏ동으로 된다 )

    // 데이터가 빈번하거나 파싱을 막 하는건 좋겠지만
    // ~~

    // 데이터 바인딩 기능 on -> build.gradle

    // 뷰 바인딩과 다르게, xml 문서의 루트( 최상위 ) 가 <laydout> 아러눈 요소로 되어 있어야
    //바인딩 클래스가 자동으로 만들어짐.

    ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        // 즉 뷰와 연결된 데이터를 가진 UUser객체를 생성
        User user = new User("sam", 20);
        binding.setUser(user);

        binding.btn.setOnClickListener(v->{

        });


    }
}