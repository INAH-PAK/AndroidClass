package com.inah_wook.ex027listviewcustom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter  extends BaseAdapter {

        Context context;    //시스템 기능을 받을 애
        ArrayList<Item> items; //아답터 설계도면을 받을 아답터
    
    
    //생성자 메소드 ~
    public MyAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }
    
    //이 메소드가 리턴 한 숫자 만큼 뷰가 만들어짐.
    @Override
    public int getCount() { return items.size(); }
    

    //파라미터의 전달된 position 의 아이템을 리턴해주는 기능 메소드
    @Override
    public Object getItem(int i) {  return items.get(i); }

    //아이템 클릭시에 position 말고 id로 구분하고자 할때 , 
    //posotion별 id를 정하는
    //기능 메소드. 
    // 통상적으로는 position과 id를 같게 만들어서 걍 id값 리턴하자.
    @Override
    public long getItemId(int i) {  return i; }

    
    //  1.   아이템 뷰 1개를 만들어서 리턴해주는 기능 메소드,
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // i : 현재 만들어야 할 아이템위치 position [ 0, 1 ,2, . . . ]
        // view : 재활용하는 view가 있다면 참조, 없으면 null
        //      : 처음에 얘가 뷰를 만들 거 아냐? 근데 어차피 같은 모양의 뷰고
        //        텍스트만 다르다면 굳이 또 뷰를 만들 필요가 없잖아 !
        //        완전 리소스 낭비잖오 ㅠ 메모리에.. 속도에..
        //        그니까 요긴 null이면 만들고, null 아니면 첨에 만든 뷰 참조하는 걸로
        //        코딩해야 굿.
        // viewGroup : ListView 참조변수

        // 1. create View : 리스트뷰에 보여질 항목(Item) 하나의 View 객체를 생성
        
        //layout 폴더 안에 있는 listview_item.xml 을 읽어와서 뷰객체로 만들어주는 
        // == 부풀려 주는 == inflate 기능 객체 !!!
        // 우리가 귀찮게 일일히 뉴 ! 리스트 뷰 !! 안해도 됨
        //  [ LayoutInflator ]
        
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }
        
        // 2. bind view : 만들어진 뷰 안에 데이터를 연결해주자 !
        ImageView iv = view.findViewById(R.id.item_iv);
        TextView tvName = view.findViewById(R.id.item_tv_name);
        TextView tvNation = view.findViewById(R.id.item_tv_nation);
        
        // 이 메소드의 첫 번째 파라미터인 위치 position 번째 데이터를 가져오기
        
        Item item = items.get(i);
        
        iv.setImageResource(item.imgId);
        tvName.setText(item.name);
        tvNation.setText(item.nation);
        
        
        
        return view;
    }
}
