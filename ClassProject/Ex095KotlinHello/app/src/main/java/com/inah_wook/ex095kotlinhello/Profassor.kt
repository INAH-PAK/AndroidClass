package com.inah_wook.ex095kotlinhello

// 보조생성자로 만들기  -> 상속받는 부모 클래스 명 옆에 () 쓰지 X
class Profassor:Person {

    var subject:String? = null

    // constructor()  // 얘가 보조생성자
                      // 보조생성자는 반????? 43:00
    constructor(name:String,age:Int,subject:String):super(name,age){
        this.subject = subject
        println(" create profassor obj")
    }

    override fun show() {
        println(" name : $name , age : $age  , subject : $subject")
    }
}