package studio.blackmonkey.chat.server.model;

import java.util.Date;

public class User {

    private String mNickname;
    private Date mLoginTime;

    public User(String name) {
        mNickname = name;
        mLoginTime = new Date();
    }

    public String getNickname() {
        return mNickname;
    }

    public Date getLoginTime() {
        return mLoginTime;
    }
}
