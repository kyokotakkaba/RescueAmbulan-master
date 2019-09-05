package com.example.abyandafa.rescueambulance.Review;

/**
 * Created by Abyan Dafa on 17/12/2017.
 */

public class Keterlibatan {
    private String created_at, lokasi, tipe_transaksi, rating_ambulan, review_ambulan;
    private Integer id_ambulan, id_transaksi, id_review;

    public Keterlibatan(String created_at, String lokasi, String tipe_transaksi, String rating_ambulan, String review_ambulan, Integer id_ambulan, Integer id_transaksi, Integer id_review) {
        this.created_at = created_at;
        this.lokasi = lokasi;
        this.tipe_transaksi = tipe_transaksi;
        this.rating_ambulan = rating_ambulan;
        this.review_ambulan = review_ambulan;
        this.id_ambulan = id_ambulan;
        this.id_transaksi = id_transaksi;
        this.id_review = id_review;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTipe_transaksi() {
        return tipe_transaksi;
    }

    public void setTipe_transaksi(String tipe_transaksi) {
        this.tipe_transaksi = tipe_transaksi;
    }

    public String getRating_ambulan() {
        return rating_ambulan;
    }

    public void setRating_ambulan(String rating_ambulan) {
        this.rating_ambulan = rating_ambulan;
    }

    public String getReview_ambulan() {
        return review_ambulan;
    }

    public void setReview_ambulan(String review_ambulan) {
        this.review_ambulan = review_ambulan;
    }

    public Integer getId_ambulan() {
        return id_ambulan;
    }

    public void setId_ambulan(Integer id_ambulan) {
        this.id_ambulan = id_ambulan;
    }

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_review() {
        return id_review;
    }

    public void setId_review(Integer id_review) {
        this.id_review = id_review;
    }
}
