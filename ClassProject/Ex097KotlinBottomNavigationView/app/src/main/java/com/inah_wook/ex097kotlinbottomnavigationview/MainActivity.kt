package com.inah_wook.ex097kotlinbottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.inah_wook.ex097kotlinbottomnavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    val fragments:MutableList<Fragment> by lazy { mutableListOf() }
    // 근데 이건 어차피 ㅁㄴ들고 시작하는거라 = 미리 만들어놔도 상관 없ㄴㄴ거라 by laze 안써도 ㄱㅊ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

            //화면에 안붙이고 배열에 넣어놨음
    fragments.add(Tab1Fragment())
    fragments.add(Tab2Fragment())
    fragments.add(Tab3Fragment())

        // 이제 이걸 화면에 붙이자

      //  supportFragmentManager.beginTransaction().add(R.id.container, fragments.get(0)).commit()
        supportFragmentManager.beginTransaction().add(R.id.container, fragments[0]).commit() //배열은 아니지만 코틀린이 배열로 표기하랭

    binding.bnv.setOnItemSelectedListener {

        supportFragmentManager.fragments.forEach{
            supportFragmentManager.beginTransaction().hide(it).commit()
        }

        val tran = supportFragmentManager.beginTransaction()

        when( it.itemId){
            R.id.bnv_menu_tab1 ->{
             //   supportFragmentManager.beginTransaction().show(fragments[0]).commit()
                tran.show(fragments[0])
            }
            R.id.bnv_menu_tab2 ->{
                if( supportFragmentManager.fragments.contains(fragments[1]))
                 //   supportFragmentManager.beginTransaction().add(R.id.container,fragments[1]).commit()
                    tran.add(R.id.container,fragments[1]).commit()
                tran.show(fragments[1])
            }
            R.id.bnv_menu_tab3 ->{
                if( supportFragmentManager.fragments.contains(fragments[2]))
                    tran.add(R.id.container,fragments[2]).commit()
                tran.show(fragments[2])
            }

        }
        tran.commit()

        //SAM 변환시는 return 이라는 단어를 아예 안써야 리턴이 됨.
       true
    }




    }
}