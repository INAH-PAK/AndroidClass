package com.inah_wook.ex030recyclerview2;

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
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_item,parent,false);


        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // 아까 예제랑 다르게 첫 번째 매게변수가 자동으로 바뀜 ~
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
//현재 벌 째 연경할 데이터를 가진 item객체를 얻어ㅇㅁ.

       Item item= items.get(position);

       holder.ivProfile.setImageResource(R.drawable.backgreoud);


    }

    // 1 . 이너 클래스 생성 :  item 한 개 뷰의 자식뷰들의 참조값을 맴버로 가지고 있는 클래스
    class VH extends RecyclerView.ViewHolder {


        CircleImageView ivProfile;
        TextView tvName;
        TextView tvMsg;
        ImageView ivImage;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.iv_profile);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            ivImage = itemView.findViewById(R.id.iv_image);


            //아이템뷰를 클릭시 반응하는 리스너 ㄱ개체 생성
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //클릭한 아이템의 위치 번호 ,  밑에 두개 다 동일
                    // int position = getAdapterPosition();
                    int position = getLayoutPosition();

                    Item item = items.get(position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(item.name + "\n" + item.massage);
                    builder.create().show();




                }
            });


        }
    }




}
