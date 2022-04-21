package com.inah_wook.ex095kotlinhello

open class Person constructor(val name:String , val age:Int){  //constructor() :  주생성자 -> val : 맴버변수로 아예 생성
    init {
        println(" create Person obj")
    }

    open fun show(){
        println(" name : $name , age : $age ")
    }




}