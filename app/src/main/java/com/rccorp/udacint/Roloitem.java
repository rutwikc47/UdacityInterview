package com.rccorp.udacint;

/**
 * Created by Rutwik on 05/01/18.
 */

public class Roloitem {

    private String mFirstName;
    private String mLastName;
    private String avatarlink;

    public Roloitem(String firstName, String lastname,String avatar) {
        mFirstName = firstName;
        mLastName = lastname;
        avatarlink=avatar;
    }

    public String getFirst() {
        return mFirstName;
    }

    public String getLast() {
        return mLastName;
    }

    public String getAvatar() {
        return avatarlink;
    }
}
