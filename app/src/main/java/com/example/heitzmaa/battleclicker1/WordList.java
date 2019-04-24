
package com.example.heitzmaa.battleclicker1;


public class WordList {

    private final int imageResource;
    private final String title;
    private final String info;

    WordList(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }


    public int getImageResource() {
        return imageResource;
    }

    String getTitle() {
        return title;
    }


    String getInfo() {
        return info;

    }
}