package com.inah_wook.ex031viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {  // 밑에 VH 이너클래스 만들고 여기에 내 VH를 익스텐즈 해줘야 함,


    //자바 메인 엑티비티 파일에 있는 참조변수 두가지를 가져와랏. 1. 운영체제 ㅡㅇ력
    Context context;
    ArrayList<Integer> imgIds;


    public MyAdapter(Context context, ArrayList<Integer> imgIds) {
        this.context = context;
        this.imgIds = imgIds;
    }

    @NonNull
    @Override //객체 생성시
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context); // 콘텍스트로부터 레이아웃을 뽑아와 !
        View itemView = inflater.inflate(R.layout.page,parent,false); // 내가 만든 시안대로 일단 만들었고
        VH vh = new VH(itemView);

        return vh; // 여기서 리턴시킨 액자가 밑의 포지션으로 감
    }

    @Override //현재 position 의 값
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.iv.setImageResource(imgIds.get(position)); //
        //이제 뷰페이저 만들러 메인 ㄱㄱ
    }

    @Override  //그때그때 만들꺼
    public int getItemCount() {

        return imgIds.size();
    }


    // 1 . inner class 생성  :  아이템 한 개의 뷰의 참조변수들을 저장하는 목적의 클래스들
    class VH extends RecyclerView.ViewHolder {

        ImageView iv;

        public VH(@NonNull View itemView) { // 이 아이템뷰가 아까 만든 페이지xml의 릴리티브 레이아웃임.
                                              //이 안의 아이템뷰가 필요하니까 ~

            super(itemView);
            iv=itemView.findViewById(R.id.iv); //이걸

        }


    }



}
