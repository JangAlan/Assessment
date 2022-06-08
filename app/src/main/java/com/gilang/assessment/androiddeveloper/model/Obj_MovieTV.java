package com.gilang.assessment.androiddeveloper.model;
public class Obj_MovieTV {
    private String title;
    private String tanggal;
    private int thumbnail;

    public Obj_MovieTV(){
    }

    public Obj_MovieTV(String title, String tanggal, int thumbnail) {
        this.title = title;
        this.tanggal = tanggal;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

