package com.inah_wook.ex091firebasechatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.VH> {

    Context context;
    ArrayList<MessageItemVO> messageItems;

    public ChatAdapter(Context context, ArrayList<MessageItemVO> messageItems) {
        this.context = context;
        this.messageItems = messageItems;
    }

    //우왕 ~~~
    // 리사이클러뷰가 보려줄 뷰의 종류(모양)이 다른 경우, , 해당 아이템마다 뷰 타입을 정하여 알려주는 리턴해주는 메소드 있음.
    final int TYPE_MY = 0;
    final int TYPE_OTHER = 1;
    @Override
    public int getItemViewType(int position) {
        if(messageItems.get(position).name.equals(G.nickname )) return TYPE_MY;
        else return TYPE_OTHER;

        // 이 메소드에서 리턴한 값이 int ==viewType 이 바로 밑 onCreateViewHolder() 에 전달됨
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // viewType : 내가 만들 뷰의 타입
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = null;
        if( viewType == TYPE_MY ) itemView = inflater.inflate(R.layout.my_message_box,parent,false);
        else itemView = inflater.inflate(R.layout.other_message_box,parent,false);
        return new VH(itemView);
    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
       MessageItemVO messageItemVO = messageItems.get(position);

       holder.tvName.setText(messageItemVO.name);
       holder.tvMsg.setText(messageItemVO.message);
       holder.tvTime.setText(messageItemVO.time);
        Glide.with(context).load(messageItemVO.profileUrl).into(holder.civ);

    }

    class VH extends RecyclerView.ViewHolder{

        // 원래는 여기에 바인딩 클래스 썼었음
        //근데 my랑 other이 있으니까 효율적이지 않아 보임.
        //지금처럼 한 뷰에 보여줄 레이아웃이 두종류이면 바인딩 클래스별로 VH를 만들어야 함.
        // 한 아이템에 한 뷰니께~~
        // 이럴땐 그냥 기존처럼 findViewById 이용할거임.->그럼 바인딩은 언제 씀?
        //근데 OtherMessageBoxBinding라고는 뜨니까 메모리 낭비잖아?
        // 이걸 xml에서 바이닝 클래스 만들지 말라고 말할 수 있음 !
        CircleImageView civ;
        TextView tvName;
        TextView tvMsg;
        TextView tvTime;
        public VH(@NonNull View itemView) {
            super(itemView);

            civ = itemView.findViewById(R.id.civ);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMsg = itemView.findViewById(R.id.tv_msg);
            tvTime = itemView.findViewById(R.id.tv_time);





        }
    }





}
