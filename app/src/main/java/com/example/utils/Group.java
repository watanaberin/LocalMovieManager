package com.example.utils;

public class Group {
    private String name;
    private int imageId;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", imageId=" + imageId +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public Group(String name, int imageId, String introduction) {
        this.name = name;
        this.imageId = imageId;
        this.introduction = introduction;
    }
}
