package com.inah_wook.ex097kotlinbottomnavigationview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inah_wook.ex097kotlinbottomnavigationview.databinding.FragmentT1Binding
import com.inah_wook.ex097kotlinbottomnavigationview.databinding.FragmentT2Binding
import com.inah_wook.ex097kotlinbottomnavigationview.databinding.FragmentT3Binding

class Tab1Fragment  : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    // 바인딩 클래스 객체 참조변수
    val binding: FragmentT1Binding by lazy { FragmentT1Binding.inflate(layoutInflater) }


}