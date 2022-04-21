package com.inah_wook.ex096kotlinviewtestinah

data class Item constructor(var title:String ,var msg:String,var img:Int)
    // 주 생성자인 constructor 의 파라미터에 var 키워드를 넣으면 맴버변수가 됨
    // 약간 파이썬같넹 __init__()
    // 심지어....
    // 클래스에 내용 없으면 중괄호도 생략 가능함 ;

// data class  :  나중에 이 클래스의 객체를 두개 만들었을 때,
// String 의 .equals()로 비교하는거 할 수 있음 ~
// 나중에 객체 두개 만들어서 비교 가능



