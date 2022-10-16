package com.bluemsun.entity;

public class Picture {
   private String image;
   private int top_num;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTop_num() {
        return top_num;
    }

    public void setTop_num(int top_num) {
        this.top_num = top_num;
    }

    public Picture(String image, int top_num) {
        this.image = image;
        this.top_num = top_num;
    }
}
