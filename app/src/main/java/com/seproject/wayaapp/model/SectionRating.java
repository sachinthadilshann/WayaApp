package com.seproject.wayaapp.model;

public class SectionRating {
    private String section,societyName, userId;
    private int rating;
    int count = 0;

    public SectionRating(String section, String societyName, String userId, int rating) {
        this.section = section;
        this.societyName = societyName;
        this.userId = userId;
        this.rating = rating;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
