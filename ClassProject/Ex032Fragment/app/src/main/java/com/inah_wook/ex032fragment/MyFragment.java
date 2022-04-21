package com.inah_wook.ex032fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//클래스는 파스칼 표기법
public class MyFragment extends Fragment {


    //  액티비티의 onCreate 처럼 프레그먼트가 화면에 보여줄 뷰를
    //  만들어 리턴해주는 작업을 하는 콜백 메소드


    //마치 여기를 액티비티처럼 생각하고 page1dml 참조변수 만들기 !
    Button btn;
    TextView tv;

    Button btn2; // 밖의 텍스트를 변하게 하는 버튼 2


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                //  1. 이미 인플레이터 줬으니 이걸로 xml 만들면 되 ㅁ~
        View view = inflater.inflate(R.layout.fragment_my,container,false); // 얘가 리턴하면 붙여야 해서 false.
        // 리사이클러 뷰에선 contaner가 패런츠


        return view;
    }

    //뷰가 만들어진 후,( ==onCreateView 실행 후 )
    //뷰들의 참조변수에 대입해주는 작업을 하기위한 callback method




    @Override   //뷰가 만들어졌을 때 ! 첫 번째 파라미터  == onCreateView의 리턴값.
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState); // 이건 아무내용 없어서 지워도됨.
        tv = view.findViewById(R.id.tv);
        btn = view.findViewById(R.id.btn);
        btn2 = view.findViewById(R.id.btn2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("nice to meet you !");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이 프레그먼트를 보여주는 액티비티 TextView  글씨를 변경해보기
                // == 메인의 textView tv 를 변경해야 함,
                // 아주 다행히 ~~
                //모든 프레그먼트는 본인을 보여주는 액티비티 객체를 소환할 수 있는 기능 메소드가 있음.

                MainActivity ac = (MainActivity) getActivity();
                ac.tv.setText("Hello Wolrd !");
            }
        });






    }




}


