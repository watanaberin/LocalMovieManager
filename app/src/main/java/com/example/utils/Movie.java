package com.example.utils;

public class Movie {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name="";
    private String introduction="";
    private String note="";
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getNote() {return note; }
    public void setNote(String note) {
        this.note = note;
    }



    public Movie(){};
    public Movie(String name,String introduction,String note) {
        this.name = name;
        this.introduction = introduction;
        this.note=note;
    }
}
