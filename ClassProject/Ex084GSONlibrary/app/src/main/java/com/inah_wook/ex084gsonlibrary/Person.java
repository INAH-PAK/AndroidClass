package com.inah_wook.ex084gsonlibrary;

public class Person {
    // 기능없는 데이터용 클래스
    // json의 name과 age 만 저장하는 데이터 분산을 막으려고 클래스 하나 만든 거.

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
