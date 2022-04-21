package com.inah_wook.ex079databainding;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import java.util.Observable;

public class User {

    // 단 이렇게 만들면 일반 자료형은 데이터 바인딩에 의해 값변경이 감시되지 않음. . .
    //String name;
    // int age;
    //그래서 관찰가능한 자료형으로 만들어야 함.
    //그래야 값변경이 갑지되서 뷰가 갱신됨
    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt age = new ObservableInt();



    //생성자
    public User(String name, int age) {
        this.name.set(name);
        this.age.set(age);

    }

    //맴버변수값을 변경하는 기능 메소드
    // ( onClick 속성에 의해 발동하는 특별한 메소드 : 규칙 ! 반드시 public, 리턴은 void, 파라미터는 무조건 뷰 하나 !)
    public  void changeData(View view){
        this.name.set("inah");
        this.age.set(27);
    }

}
