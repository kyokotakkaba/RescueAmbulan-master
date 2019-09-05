package com.example.abyandafa.rescueambulance.Review;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.Report.PengaduanActivity;
import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewList extends AppCompatActivity implements AdapterReview.AdapterReviewListener{

    private RecyclerView rv;
    private AdapterReview rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private SPAmbulan spAmbulan;
    private ArrayList<Keterlibatan> daftarKeterlibatan;
    private SearchView searchView;
    private ProgressDialog progressDialog;




    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public TextView namaPengguna;
    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        spAmbulan = new SPAmbulan(this);
        setDrawer();


        rv = (RecyclerView) findViewById(R.id.rv);
        rvLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(rvLayoutManager);

        daftarKeterlibatan = new ArrayList<>();

        rvAdapter = new AdapterReview(daftarKeterlibatan, this);
        searchView = (SearchView) findViewById(R.id.searching);
        searchView.setActivated(true);
        searchView.setQueryHint("Cari tanggal kejadian");
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setBackgroundColor(Color.WHITE);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        spAmbulan = new SPAmbulan(this);
        updateFCM(refreshedToken);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);

        rv.setAdapter(rvAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rvAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvAdapter.getFilter().filter(newText);
                return false;
            }
        });
        panggilListKeterlibatan();







    }

    private void setDrawer() {

        //inisialisasi button toolbar (tombol di kiri atas)
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inisialisasi navigation view(bagian sebelah kiri layar ketika toolbar di klik
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        headerView = navigationView.getHeaderView(0);

        namaPengguna = (TextView) headerView.findViewById(R.id.namaPengguna);
        namaPengguna.setText(spAmbulan.getSpUsername());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                drawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    //pindah ke halaman profil pengguna
                    case R.id.navigation1:
                        return true;
                    //pindah ke halaman pengaduan
                    case R.id.navigation2:
                        Intent intent = new Intent(ReviewList.this, PengaduanActivity.class);
                        startActivity(intent);


                        return true;

                    //pengguna logout
                    case R.id.keluar:
                        progressDialog.show();
                        spAmbulan.logout(spAmbulan.getSpToken());
                        return true;
                    default:

                        return true;
                }

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer,
                R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();



    }

    private void panggilListKeterlibatan() {
        progressDialog.show();
        Log.d("masuk", "panggilListKeterlibatan: ");
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);


        Call<APIResponse> result = apiClient.getListReview(spAmbulan.getSpToken());

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code() ==200)
                {
                    Log.d("berhasil", "onResponse: ");
                    Gson gson = new Gson();
                    List<Keterlibatan> list= gson.fromJson(gson.toJson(response.body().getData()), new TypeToken<ArrayList<Keterlibatan>>() {}.getType());
                    daftarKeterlibatan.clear();
                    daftarKeterlibatan.addAll(list);
                    Collections.reverse(daftarKeterlibatan);
                    rvAdapter.notifyDataSetChanged();

                }
                else if(response.code()==202)
                {
                    spAmbulan.logout(1);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d(t.getMessage(), "onFailure: ");
                progressDialog.dismiss();
            }
        });





//        result.enqueue(new Callback<APIResponse>() {
//            @Override
//            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//
//                if(response.code()==200)
//                {
//                    Log.d("berhasil", "onResponse: ");
//                    Gson gson = new Gson();
//                    daftarKeterlibatan= gson.fromJson(gson.toJson(response.body().getData()), new TypeToken<ArrayList<Keterlibatan>>() {}.getType());
//                    AdapterReview adapterReview= new AdapterReview(daftarKeterlibatan);
//                    rv.setAdapter(adapterReview);
//                    setClickRv();
//
//                }
//                else if(response.code()==202)
//                {
//                    spAmbulan.logout(1);
//                }
//                else
//                {
//                    Log.d(response.message(), "onResponse: ");
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<APIResponse> call, Throwable t) {
//                Toast.makeText(ReviewList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(t.getMessage(), "onFailure: ");
//            }
//        });

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        panggilListKeterlibatan();
    }



    private void updateFCM(String token) {

        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.updateFCM(spAmbulan.getSpToken(), token);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.code() == 200) {
                    Log.d("fcm", "onResponse: ");
                } else if (response.code() == 202) {
                    spAmbulan.logout(1);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("gagal", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onReviewSelected(Keterlibatan keterlibatan) {

        Intent intent = new Intent(ReviewList.this, KeterlibatanAmbulan.class);
        intent.putExtra("transaksi", keterlibatan.getId_transaksi().toString());
        startActivity(intent);
    }
}
