package com.inah_wook.ex078viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inah_wook.ex078viewbinding.databinding.RecyclerItemBinding;

import java.util.AbstractList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

//2. 구현해야 할 함수들 임플리먼트들 쭈루룩
    Context context;
    AbstractList<Item> items;

    public MyAdapter(Context context, AbstractList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 3.
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false); // 리턴시키면 자동으로 만드니까 false
        return new VH(itemView); // 결국 뷰 홀더를 리턴시캿음.
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
//5
//        //
//        Item item = items.get(position);
//
//        // 근데 여긴 두개니까 망정이지~
//        // 맨 밑에 홀더 ㄱㄱ
//        holder.binding.tv.setText(item.title);
//        holder.binding.iv.setImageResource(item.imgId);

        //이게 실무에서 많이 쓰는 코드임. 맨 밑에 기능 메소드에 값 전달
        holder.bindItem(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //1. 뷰홀더 만들기
    class VH extends RecyclerView.ViewHolder{

        //recycler_item.xml 과 연결을 담당하는 바인딩 클래스가 자동으로 존재함
        // 이 바인딩 클래스느 xml 문서의 이름을 기반으로클래스 이름이 마들어짐.
        RecyclerItemBinding binding;

        public VH(@NonNull View itemView) {
            super(itemView);
//4
            //원래는 binding = RecyclerItemBinding.inflate(itemView) 했는데
            //여기서는 이미 위에서 뷰를 만들었으니까 바인드 메소드 ~
            binding = RecyclerItemBinding.bind(itemView);
        }

        //R
        void bindItem(Item item){
            // 이 메소드 실행시 아이템 받아오는 기능 메소드
            binding.iv.setImageResource(item.imgId);
            binding.tv.setText(item.title);
        }



    }



}
