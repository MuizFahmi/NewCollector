package com.example.newcollector;

import java.util.Comparator;

public class Card {
    private String Title;
    private String Category;
    private String Description;
    private int Thumbnail;

    public Card(String title, String category, String description, int thumbnail) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    public static Comparator<Card> CardAlphabetComparator = new Comparator<Card>() {
        @Override
        public int compare(Card book, Card t1) {
            return book.getTitle().compareTo(t1.getTitle());
        }
    };

    public static Comparator<Card> CardTypeComparator = new Comparator<Card>() {
        @Override
        public int compare(Card book, Card t1) {
            return book.getCategory().compareTo(t1.getCategory());
        }
    };

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
}
