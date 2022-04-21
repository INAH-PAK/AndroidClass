package com.inah_wook.ex068googlemap;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    //xml에서 만든 지도 프래그먼트 찾아오기
        // 여기서 내 안에 있는 프레그먼트를 찾고 싶으므로 자식 프레그먼트 매니저를 부름.
        // 메인에서는 서포트 프레그먼트 매니저를 찾아옴.
        // 근데 모든 부모들은 자식을 관리하기때문에 부모 불러도 되지만 엄밀히 말해서 자식을 써야 하니까 여기선 자식을 쓰자.
        FragmentManager manager = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                 // 이대로만 써도 에러 안나야 함 ~
            }
        });

    }
}

