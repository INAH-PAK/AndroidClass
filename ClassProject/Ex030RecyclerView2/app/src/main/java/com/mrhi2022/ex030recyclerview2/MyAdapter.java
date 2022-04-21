package com.mrhi2022.ex030recyclerview2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item, parent, false);

        VH holder= new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        //현재 연결한 데이터를 가진 Item객체 얻어오기
        Item item= items.get(position);

        holder.ivProfile.setImageResource(item.profileId);
        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.message);
        holder.ivImage.setImageResource(item.imageId);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //inner class
    //아이템 1개 뷰의 자식뷰들의 참조값을 멤버로 가지고 있는 클래스
    class VH extends RecyclerView.ViewHolder {

        CircleImageView ivProfile;
        TextView tvName;
        TextView tvMsg;
        ImageView ivImage;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivProfile= itemView.findViewById(R.id.iv_profile);
            tvName= itemView.findViewById(R.id.tv_name);
            tvMsg= itemView.findViewById(R.id.tv_msg);
            ivImage= itemView.findViewById(R.id.iv_image);

            //itemView를 클릭할 때 반응하는 리스너 객체 생성 및 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭한 아이템의 위치 번호
                    int position= getLayoutPosition();

                    //해당하는 데이터를 가진 Item객체 얻어오기
                    Item item= items.get(position);

                    AlertDialog.Builder builder= new AlertDialog.Builder(context);
                    builder.setMessage(item.name+"\n"+item.message);
                    builder.create().show();
                }
            });

        }
    }
}
