package studio.blackmonkey.chat.server.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Message {

    /*
     * The member marked with @Autowired can be automatically initialized via searching setter/getter.
     */

    @Autowired private String mSender;
    @Autowired private String mReceiver;
    @Autowired private String mContent;
    @Autowired private int[] mTime;

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String receiver) {
        mReceiver = receiver;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int[] getTime() {
        return mTime;
    }

    public void setTime(int[] time) {
        mTime = time;
    }
}
