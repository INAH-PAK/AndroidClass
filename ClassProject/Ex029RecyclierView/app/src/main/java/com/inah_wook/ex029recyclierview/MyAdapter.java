package com.inah_wook.ex029recyclierview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {


    Context context;
    ArrayList<Item> items ;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }


    //얘가 알아서 재활용 할 뷰 없으면 만들고, 있으면 안만듬~
    // 재활용 할 뷰가 없으면 실행되는 콜백메소드.
    // 항목( Item) 뷰를 만들고 참조값을 가진 viewHolder 를 리턴해주는 리소드
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recycler_items,parent,false);
        // 위에는 recycler_items.xml 파일에 있는 뷰를 parent에게 줘. 근데 지금당장 만들지는 말고 부르면 만들어 = false
        //parent 는 리사이클 뷰라고도 보면 됨.

        ViewHolder holder = new ViewHolder(itemView);
        return holder;

    }

    //위에서 리턴한 값 holder 가 요 함수의 첫 번째 값으로 받음.
    // 해당 위치 (만들어야 할 위치 = position) 의 항복뷰에 items의 값을 연결하는 메소드 !
    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // 왜 다운캐스팅 함?
        // 왜냐면 리턴한 클래스가 ViewHolder 클래스이고, 받는 클래스가  RecyclerView.ViewHolder 클래스라서.
        ViewHolder vh = (ViewHolder)holder;

        //이제 해당번째 아이템 얻어오기
        Item item =items.get(position);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.message);




    }

    @Override //그냥 전체 사이즈 반환
    public int getItemCount() {
        return items.size();
    }


    //이너 클래스로 holder를 만들어주자 ~
    //아이템 뷰들의 자식참조변수들을 저장하고 있을 클래스
    // == 아이템 뷰를 저장하는 뷰 홀더 클래스
    // 뷰 하나를 생성시, 참조값을 불러드리려는 목적,

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvMsg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);

            // itemView 가 클릭되었을때 반응하는 리스너 객체 생성 밑 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭한 아이템뷰가 몇 번째 위치 ?
                    // 이 뷰홀더에게 클래스 객체에세 현재 위치를 알 수 있는 기능이 있음.
                    // int position = ViewHolder.this.getAdapterPosition(); //레이아웃 포지션은 화면에 보여지는 순서값이라우린 아답터로 쓰자.
                    int position = getAdapterPosition(); // 어차피 이너클래스라 생략해서 씀.
                    Item item = items.get(position);
                    Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }








}
