package com.mrhi2022.ex078viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mrhi2022.ex078viewbinding.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    //fragment_my.xml 과 연결을 담당하는 클래스가 자동으로 만들어져 있음.
    //그 클래스의 이름은 레이아웃파일명을 기반으로 제작되어 있음.
    FragmentMyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentMyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(v->{
            binding.tv.setText("Nice view binding");
        });
    }
}
