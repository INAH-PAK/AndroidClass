package com.inah_wook.ex033fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {


    //먼저, 이 프레그먼트가 보여줄 뷰를 만들어 리턴해주는 콜백 메소드
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 이제 여기 매개변수인 인플레이터로 아까 만든 프레그먼트 레이아웃을 객체로 생성
        View view = inflater.inflate(R.layout.fragment_my,container,false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fragment 는 자신을 보여주는 액티비티를 가져올 수 있다 !

        //전달받은 argument 를 받아오기
        Bundle bundle = getArguments();
        String name = bundle.getString("name",""); // defaltvalue 는 식별자가 없을 때 넣을 값.
        int age = bundle.getInt("age",-1);

        Toast.makeText(getActivity(), name + " " + age, Toast.LENGTH_SHORT).show();
    }
}
