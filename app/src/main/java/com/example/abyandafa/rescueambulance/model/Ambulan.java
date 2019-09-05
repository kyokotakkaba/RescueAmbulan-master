package com.example.abyandafa.rescueambulance.model;

/**
 * Created by Abyan Dafa on 05/12/2017.
 */

public class Ambulan {

    private String username;
    private String kode;
    private String token;
    private String tokenFCM;
    private String no_pol_ambulan;
    private String alamat_rs;

    private String nama_rs;

    private Integer id_ambulan;
    public Ambulan(String username, String kode, String token, String no_pol_ambulan, String alamat_rs, String nama_rs, Integer id_ambulan) {
        this.username = username;
        this.kode = kode;
        this.token = token;
        this.no_pol_ambulan = no_pol_ambulan;
        this.alamat_rs = alamat_rs;
        this.nama_rs = nama_rs;
        this.id_ambulan = id_ambulan;
    }

    public String getNama_rs() {
        return nama_rs;
    }

    public void setNama_rs(String nama_rs) {
        this.nama_rs = nama_rs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNo_pol_ambulan() {
        return no_pol_ambulan;
    }

    public void setNo_pol_ambulan(String no_pol_ambulan) {
        this.no_pol_ambulan = no_pol_ambulan;
    }

    public String getAlamat_rs() {
        return alamat_rs;
    }

    public void setAlamat_rs(String alamat_rs) {
        this.alamat_rs = alamat_rs;
    }

    public Integer getId_ambulan() {
        return id_ambulan;
    }

    public void setId_ambulan(Integer id_ambulan) {
        this.id_ambulan = id_ambulan;
    }
}
