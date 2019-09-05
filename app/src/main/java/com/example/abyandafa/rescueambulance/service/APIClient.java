package com.example.abyandafa.rescueambulance.service;

import com.example.abyandafa.rescueambulance.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Abyan Dafa on 05/12/2017.
 */

public interface APIClient {
    @FormUrlEncoded
    @POST("login/ambulan")
    Call<APIResponse> loginApi(@Field("username") String username, @Field("kode") String kode);

    @FormUrlEncoded
    @POST("ambulan/fcmtoken")
    Call<APIResponse> updateFCM(@Field("token") String token, @Field("fcm") String fcmToken);

    @FormUrlEncoded
    @POST("ambulan/terima")
    Call<APIResponse> terimaTransaksi(@Field("token") String token, @Field("id_transaksi") String transaksi, @Field("id_ambulan")
                                      String ambulan);

    @FormUrlEncoded
    @POST("ambulan/verifikasi")
    Call<APIResponse> verifikasiTransaksi(@Field("token") String token, @Field("id_transaksi") String transaksi, @Field("id_ambulan") String ambulan);


    @FormUrlEncoded
    @POST("ambulan/selesai")
    Call<APIResponse> selesaiTransaksi(@Field("token") String token, @Field("id_transaksi") String transaksi, @Field("followup")
            String followup);

    @FormUrlEncoded
    @POST("review/ambulan/list")
    Call<APIResponse> getListReview(@Field("token") String token);

    @FormUrlEncoded
    @POST("review/ambulan")
    Call<APIResponse> reviewPengguna(@Field("token") String token, @Field("id_transaksi") String id_transaksi, @Field("id_ambulan") String id_ambulan, @Field("review_ambulan")
            String review_ambulan, @Field("rating_ambulan") String rating_ambulan);

    @FormUrlEncoded
    @POST("ambulan/transaksi/detail")
    Call<APIResponse> transaksiDetail(@Field("token") String token, @Field("id_transaksi") String idtransaksi);

    @FormUrlEncoded
    @POST("pengaduan/ambulan")
    Call<APIResponse> uploadPengaduan(@Field("token") String token, @Field("pesan") String pesan);

    @FormUrlEncoded
    @POST("logout/ambulan")
    Call<APIResponse> logout(@Field("token") String token);





}
