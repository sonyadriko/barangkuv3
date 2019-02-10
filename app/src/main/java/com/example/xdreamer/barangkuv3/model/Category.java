package com.example.xdreamer.barangkuv3.model;

public class Category {
    private String nama;
    private String image;

    public Category() {
    }

    public Category(String nama, String image) {
        nama = nama;
        image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
