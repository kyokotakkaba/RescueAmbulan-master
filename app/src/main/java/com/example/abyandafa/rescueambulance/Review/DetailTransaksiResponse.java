package com.example.abyandafa.rescueambulance.Review;

/**
 * Created by Abyan Dafa on 08/01/2018.
 */

class DetailTransaksiResponse {
    private String id_transaksi, lokasi, waktu_kejadian, waktu_pertolongan_awam, waktu_pertolongan_ambulan, waktu_selesai
            , id_ambulan, plat_ambulan, followup, rating, komentar;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getWaktu_kejadian() {
        return waktu_kejadian;
    }

    public void setWaktu_kejadian(String waktu_kejadian) {
        this.waktu_kejadian = waktu_kejadian;
    }

    public String getWaktu_pertolongan_awam() {
        return waktu_pertolongan_awam;
    }

    public void setWaktu_pertolongan_awam(String waktu_pertolongan_awam) {
        this.waktu_pertolongan_awam = waktu_pertolongan_awam;
    }

    public String getWaktu_pertolongan_ambulan() {
        return waktu_pertolongan_ambulan;
    }

    public void setWaktu_pertolongan_ambulan(String waktu_pertolongan_ambulan) {
        this.waktu_pertolongan_ambulan = waktu_pertolongan_ambulan;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public String getId_ambulan() {
        return id_ambulan;
    }

    public void setId_ambulan(String id_ambulan) {
        this.id_ambulan = id_ambulan;
    }

    public String getPlat_ambulan() {
        return plat_ambulan;
    }

    public void setPlat_ambulan(String plat_ambulan) {
        this.plat_ambulan = plat_ambulan;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public DetailTransaksiResponse(String id_transaksi, String lokasi, String waktu_kejadian, String waktu_pertolongan_awam, String waktu_pertolongan_ambulan, String waktu_selesai, String id_ambulan, String plat_ambulan, String followup, String rating, String komentar) {
        this.id_transaksi = id_transaksi;
        this.lokasi = lokasi;
        this.waktu_kejadian = waktu_kejadian;
        this.waktu_pertolongan_awam = waktu_pertolongan_awam;
        this.waktu_pertolongan_ambulan = waktu_pertolongan_ambulan;
        this.waktu_selesai = waktu_selesai;
        this.id_ambulan = id_ambulan;
        this.plat_ambulan = plat_ambulan;
        this.followup = followup;
        this.rating = rating;
        this.komentar = komentar;

    }
}
