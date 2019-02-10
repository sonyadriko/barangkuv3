package com.example.xdreamer.barangkuv3.model;

public class DataBarang {
    private String namabarang;
    private String deskbarang;
    private int hargabarang;
    private String imagebarang;
    private String categoryId;

    public DataBarang() {
    }

    public DataBarang(String namabarang, String deskbarang, int hargabarang, String imagebarang, String categoryId) {
        this.namabarang = namabarang;
        this.deskbarang = deskbarang;
        this.hargabarang = hargabarang;
        this.imagebarang = imagebarang;
        this.categoryId = categoryId;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getDeskbarang() {
        return deskbarang;
    }

    public void setDeskbarang(String deskbarang) {
        this.deskbarang = deskbarang;
    }

    public int getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(int hargabarang) {
        this.hargabarang = hargabarang;
    }

    public String getImagebarang() {
        return imagebarang;
    }

    public void setImagebarang(String imagebarang) {
        this.imagebarang = imagebarang;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
