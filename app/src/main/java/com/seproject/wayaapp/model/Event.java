package com.seproject.wayaapp.model;

public class Event {

    String society,title, desc, id,exp;
    int count = 0;

    public Event(String society,String title, String desc, String id,String exp) {
        this.society = society;
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.exp = exp;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
