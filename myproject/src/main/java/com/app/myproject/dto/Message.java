package com.app.myproject.dto;

public class Message {

    private String from;

    private String message;

    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEFT
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
