package com.example.newcollector;

import java.util.Comparator;

public class newCard {
    private String Title;
    private String Category;
    private String Description;
    private int Thumbnail;
    private Boolean button;

    public newCard(String title, String category, String description, int thumbnail, Boolean buttons) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        button = buttons;
    }

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public Boolean getButton() { return button; }
}
