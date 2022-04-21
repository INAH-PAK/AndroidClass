package com.inah_wook.ex034fragmentpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {   // 뷰가 아닌, Fragment 생성시 FragmentStateAdapter

    //Fragment 갹체를 참조하는 참조변수 3개를 가지고 있는 배열객체 1개 생성 !
    Fragment[] fragments = new Fragment[3];

    // 이제 생성자 만듬 ~

    //fragmentActivity 를 알려면 MainActivity 봐야 함,
    //  (상속구조)  :  Context <- Activity <- FragmentActivity <- AppCompatActivity <-MainActivity
    //그러니까 아답터는 FragmentActivity 능력이 필요했던 거임! 그래야 동적으로 add ~~하지 ~~

    public MyAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        //이 생성자에서 페이지별로 보여줄 fragment 객체 생성 및 배열요소로 추가,
        fragments[0] = new Page1Fragment();
        fragments[1] = new Page2Fragment();
        fragments[2] = new Page3Fragment();

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //값설정은 어차피 자기 자바들이 있으니까, 여기선 만들기만 하면 됨.

        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }



}
