package com.inah_wook.ex087retofittwomarket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inah_wook.ex087retofittwomarket.databinding.RecyclerItemBinding;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
//꼭 외우깅
public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.VH> {
        Context context;
        ArrayList<ItemVO> items;

    public MarketAdapter(Context context, ArrayList<ItemVO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itwmView = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new VH(itwmView)  ;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ItemVO item = items.get(position);
        holder.binding.tvTitle.setText("gg");
        holder.binding.tvMsg.setText(item.message);
        holder.binding.vtPrice.setText(item.title + "원");


        // load 의 링크 이미지는..
        // 우린 지금 DB에 저장된 이미지를 불러와야 하잖아?
        // 근데 지금 DB에는 파일 경로만 저장되어 있음
        // 근데 item.file 변수에는 DB에 저장된 이미지의 상대주소 ( host 주소가 없이 경로만 있는) 값이 저장되어 있음
        // 근데 글라이드는 load("http://~~~/파일경로") 이걸 넣어야 읽히잖아?
        // 그래서 호스트주소가 있는 절대경로를 만들어야 함.
        //
        String imgUrl="http://commit.dothome.co.kr/05/"+item.file;
        Glide.with(context).load(imgUrl).into(holder.binding.iv);
        Log.i("이미지 링크 가능?",imgUrl);

        // 자 실무에서는 DB에 좋아요만 몰아넣는 테이블을 만듬.
        // no, name(ID값), market_no(원래 테이블의 no값)
        // relation : 무결성 -> 테이블을 연결함.
        // 만일, 1 테이블의 값을 2에 넣으려는데, 없는 값이면 허용 안하는,,
        // 이거 해보쟝 ~
        //EDBMS - MYSQL. . .
        // select 마켓 from 페이버 테이블 where

        // 트루 펄스 고민 ㄱ


        // 왔냐 ~~~
            holder.binding.tvFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    // 두번째 파라미터 boolean b : 체크상태 알려줌
                    if(b){
                        Toast.makeText(context, item.title+" 의 관심이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        // 여기에 디비 저장하는 코드
                    }else{
                        Toast.makeText(context, item.title+" 의 관심이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                        // 여기에 디비 저장하는 코드
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    class VH extends RecyclerView.ViewHolder{
        RecyclerItemBinding binding;
        public VH(@NonNull View itemView) {

            super(itemView);
            binding=RecyclerItemBinding.bind(itemView);

        }
    }


}
