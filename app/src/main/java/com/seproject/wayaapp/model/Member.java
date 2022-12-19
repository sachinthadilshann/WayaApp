package com.seproject.wayaapp.model;

public class Member {

    private String societyName, id, name;
    private Boolean valid;
    int count = 0;

    public Member(String societyName, String id,String name,boolean valid) {
        this.societyName = societyName;
        this.id = id;
        this.name=name;
        this.valid=valid;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
