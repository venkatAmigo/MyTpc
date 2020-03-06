package com.vvitmdc.chats.model;

public class Comment {
    private String pid;
    private String uid;
    private String comment;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment() {
    }

    public Comment(String pid, String uid, String comment) {
        this.pid = pid;
        this.uid = uid;
        this.comment = comment;
    }
}
