package com.inah_wook.ex028listviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {

    ArrayList<String> items;
    Context context;

    public MyAdapter(Context context, ArrayList<String> items ){

        this.items = items;
        this.context = context;

    }


    //갯수
    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    //리스트 뷰가 보여줄 뷰를 아이템 1개 뷰 객체를 만들어 리턴해주는 기능 메소드
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //   i  :  현재 만들어야 할 위치 포지션 ==> 어딜 만들지
        //   view : 재활용 할 뷰 있으면 참조. 있으면 null

        // 할일 1. create view
        if(view == null){
            //layout 폴더 안에 있는 list_items.xml 파일을 읽어와서 뷰 객체를로
            //만들어주는 능력을 가진 객체 소환

            LayoutInflater inflater= LayoutInflater.from(context);
            view= inflater.inflate(R.layout.list_layout, viewGroup, false);


            ViewHolder holder=new ViewHolder(view);
            view.setTag(holder);


        }

        // 할일 2. bind view
        //만들어진 뷰 안에 있는 TextView를 찾아오는  findViewById 메소드가 속도릴 존나 저하시킴 ;;
        //   [  ViewHolder  ]
        // 슝 리스트들이 있는데 각각의 뷰에 객체를 달아놓음.
        // 그 동안 안알려줬지만, 모든 뷰는 '태그'라는 변수가 있음 . 이미지뷰든 리니어 레이아웃이든
        // 태그라는 마술 주머니를 넣어둘 수 있음.
        // 요 태그는 아무거나 다 넣을 수 있음.

        // 자, 원래 일일히 우리가 그 상황이 되면 매번바인드 해야하잖아?




       // TextView tv =view.findViewById(R.id.item_tv);

        //뷰 안에 태그로 지정된 ViewHolder 객체를 꺼내오기
        ViewHolder holder = (ViewHolder) view.getTag();

        String item = items.get(i);
        holder.tv.setText(item);


        return view;
    }


    //뷰 홀더 이너클래스 생성
    class ViewHolder{
        // 아이템 한 개의 뷰의 자식 뷰들의 참조변수를 맴버로 거지는 클래스 ==> list_layout 요기에 넣어있는 고 말하는 거임.
        TextView tv;

        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.item_tv);
        }

    }
























}// MyAdapter
