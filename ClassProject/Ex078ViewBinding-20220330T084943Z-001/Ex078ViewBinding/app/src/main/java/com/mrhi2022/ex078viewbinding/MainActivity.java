package com.mrhi2022.ex078viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mrhi2022.ex078viewbinding.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //findViewById()메소드의 번거로움과 성능문제를 해결한
    //안드로이드의 기능임. - 라이브러리가 아님.
    //단, 이 기능은 기본프로젝트에서는 off되어 있음.
    //그 기능을 on만 하면됨. build.gradle 에서 작성

    //view binding기술의 원리.
    //xml레이아웃파일(activity_main.xml)의 뷰들을 이미 연결(bind)하고 있는
    //클래스가 자동으로 만들어져 있음.
    //이 클래스의 이름은 xml 문서의 이름을 기반으로 만들어 져 있음.

    //activity_main.xml 의 뷰들을 참조하는 바인딩객체의 참조변수
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //activity_main.xml 문서를 기반을 뷰객체들을 생성(inflate)하여 바인딩객체 생성
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        //액티비티에게 보여줄 내용물뷰(ContentView)를 binding 객체의 뷰로 설정
        setContentView(binding.getRoot());

        //바인딩 객체안에 있는 id가 "tv"인 TextView의 글씨 변경
        //이미 바인딩이 되어 있기에 별도의 findViewById()나 TextView참조변수 필요없음
        //바인딩 객체안에 id를 기반으로 각 뷰와 연결된 참조변수들이 이미 만들어져 있음.
        binding.tv.setText("Nice");
        binding.btn.setOnClickListener(v->binding.tv.setText("nice to meet you."));

        //롱~클릭 리스너의 원래방법
//        binding.btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                return true;
//            }
//        });

        //롱~클릭 리스너의 람다식 처리
        binding.btn.setOnLongClickListener( view->{
            Toast.makeText(this, "long clicked", Toast.LENGTH_SHORT).show();
            return true;
        });


        binding.btn2.setOnClickListener(v->{
            binding.tv2.setText( binding.et.getText().toString() );
            binding.et.setText("");
        });

        //동적으로 MyFragment객체 붙이기
        MyFragment fragment= new MyFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

    }

    //대량의 데이터들
    ArrayList<Item> items= new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();

        //대량의 데이터들 추가
        items.add( new Item(R.drawable.newyork, "new york")  );
        items.add( new Item(R.drawable.paris, "paris")  );
        items.add( new Item(R.drawable.sydney, "sydney")  );
        items.add( new Item(R.drawable.newyork, "뉴욕")  );
        items.add( new Item(R.drawable.paris, "파리")  );
        items.add( new Item(R.drawable.sydney, "시드니")  );

        //아답터 객체 생성 및 리사이클러뷰에 연결
        adapter= new MyAdapter(this, items);
        binding.recyclerview.setAdapter(adapter);
    }
}