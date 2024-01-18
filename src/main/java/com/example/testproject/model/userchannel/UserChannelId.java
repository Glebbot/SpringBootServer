package com.example.testproject.model.userchannel;

import java.io.Serializable;

public class UserChannelId implements Serializable {
    private long user_id;
    private long channel_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }
}
