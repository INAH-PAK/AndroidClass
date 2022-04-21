package com.inah_wook.ex095kotlinhello

open class Student constructor(name:String,age:Int,val major:String) : Person(name,age) { // val O : 맴버변수 생성 , val X : 매개변수로 받아옴
    init {
        println("create Student obj")
    }

    // override시, 자동으로 open으로 적용 됨.
    override fun show() {
        //super.show()
        println(" name : $name , age : $age , major , $major")

    }

}