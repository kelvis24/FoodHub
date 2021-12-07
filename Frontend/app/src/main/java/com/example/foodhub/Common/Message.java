package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {

    private int who;
    private int sequence;
    private String message;

    public Message(JSONObject obj) throws JSONException {
        this.who = obj.getInt("who");
        this.sequence = obj.getInt("sequence");
        this.message = (String)obj.get("message");
    }

    public Message(int who, int sequence, String message) {
        this.who = who;
        this.sequence = sequence;
        this.message = message;
    }

    public int getWho() {
        return who;
    }

    public int getSequence() {
        return sequence;
    }

    public String getMessage() {
        return message;
    }

}
