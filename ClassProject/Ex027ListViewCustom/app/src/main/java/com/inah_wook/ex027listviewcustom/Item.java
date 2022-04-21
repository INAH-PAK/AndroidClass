package com.inah_wook.ex027listviewcustom;

//대량의 데이터 1개 아이템의 데이터를 저장 할 객체 설계도면
//바로 요기가 커스텀 Adapter !
//한 항목에 들어갈 데이터들을 추상화 해줘야 함.
public class Item {

    int imgId ;
    String name;
    String nation;

    public Item( String name, String nation, int imgId){

        this.name = name;
        this.nation = nation;
        this.imgId = imgId;

    }

    // 이렇게 먼저 어답터 설계도면을 만든 후, 어답터를 만들러 가자 ! -> MyAdapter Class ㄱㄱ

}
