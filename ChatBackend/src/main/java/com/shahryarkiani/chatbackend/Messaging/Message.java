package com.shahryarkiani.chatbackend.Messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("messages")
@CompoundIndex(name = "tofrom", def ="{'to': 1, 'from': 1}")
@CompoundIndex(name = "fromto", def ="{'from': 1, 'to': 1}")
public class Message {

    @Id
    private String id;

    private String from;
    @JsonProperty(required = true)
    private final String to;
    @JsonProperty(required = true)
    private final String msgBody;

    @JsonCreator
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
