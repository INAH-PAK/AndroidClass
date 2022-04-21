package com.mrhi2022.ex078viewbinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrhi2022.ex078viewbinding.databinding.RecyclerItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

//        Item item= items.get(position);
//        holder.binding.tv.setText(item.title);
//        holder.binding.iv.setImageResource(item.imgId);

        holder.bindItem(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        //recycler_item.xml 과 연결을 담당하는 바인딩클래스가 자동으로 존재함
        //이 바인딩 클래스는 xml 문서의 이름을 기반으로 클래스이름이 만들어짐.
        RecyclerItemBinding binding;

        public VH(@NonNull View itemView) {
            super(itemView);
            binding= RecyclerItemBinding.bind(itemView);
        }

        void bindItem(Item item){
            binding.iv.setImageResource(item.imgId);
            binding.tv.setText(item.title);
        }
    }
}
