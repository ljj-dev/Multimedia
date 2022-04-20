package com.example.multimedia.Message;

public class Msg {
    //消息的类型有两个可选值
    public static final int TYPE_RECEIVED = 0;      //TYPE_RECEIVED 表示这是一条收到的消息
    public static final int TYPE_SEND = 1;      //TYPE_SEND 表示这是一条发出的消息
    //Msg 类中只有两个字段
    private String content;     //content 表示消息的内容
    private int type;       //type 表示消息的类型；

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
