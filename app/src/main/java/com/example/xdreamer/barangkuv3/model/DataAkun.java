package com.example.xdreamer.barangkuv3.model;

public class DataAkun {
    private String nama;
    private String email;
    private String nohp;
    private String password;

    public DataAkun() {
    }

    public DataAkun(String nama, String email, String nohp, String password) {
        this.nama = nama;
        this.email = email;
        this.nohp = nohp;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
