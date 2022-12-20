package com.seproject.wayaapp.model;

public class SectionRating {
    private String section;
    private int rating;
    int count = 0;

    public SectionRating(String section, int rating) {
        this.section = section;
        this.rating = rating;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
