package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private Integer userid;

    public Note() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String toString() {
        return id + " " + title + " " + description + " " + userid;
    }
}
