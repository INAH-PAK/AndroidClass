package com.inah_wook.ex095kotlinhello


//class Alba constructor(name:String,age:Int,major:String,val tack:String) : Student(name,age,major) {
//constructor는 생략이 가능

class Alba(name:String,age:Int,major:String,val tack:String) : Student(name,age,major) {
    init {
        println(" Create Alba Student Instunce")
    }

    override fun show() {
        println("  name : $name , age : $age , major : $major  , task : $tack")
    }
}