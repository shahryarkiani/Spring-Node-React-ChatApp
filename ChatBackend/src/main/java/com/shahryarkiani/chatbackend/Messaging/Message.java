package com.shahryarkiani.chatbackend.Messaging;

public class Message {

    private Long id;

    private String from;
    private final String to;

    private final String msgBody;

    public Message(String to, String msgBody){
        this.to = to;
        this.msgBody = msgBody;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMsgBody() {
        return msgBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msgBody='" + msgBody + '\'' +
                '}';
    }
}
