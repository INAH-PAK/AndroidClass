package com.inah_wook.ex091firebasechatting;

public class MessageItemVO {
    public String name; // 객체 통ㅊ로 올리려면 퍼브릭!
    public String message;
    public String time;
    public String profileUrl;

    public MessageItemVO() {
    }

    public MessageItemVO(String name, String message, String time, String profileUrl) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profileUrl = profileUrl;
    }


}
