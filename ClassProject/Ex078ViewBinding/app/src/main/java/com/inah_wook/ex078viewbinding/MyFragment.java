package com.inah_wook.ex078viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inah_wook.ex078viewbinding.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    // fragmrnt_my.xml 과 연결을 담당하는 클래스가 다공으로 만들어져 있음.
    // 그 클래스의 이름은 레이아웃파일명으로 자동으로 제작되어 있음.
    FragmentMyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater,container,false);

        // getRoot() 는 가장 큰 릴리티브 레이아웃임.

        return binding.getRoot();

    }// onCreat

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(v-> {binding.tv.setText(" 안녕  ~ ");});

    }
}
