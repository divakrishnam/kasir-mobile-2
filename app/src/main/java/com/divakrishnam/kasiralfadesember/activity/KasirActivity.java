package com.divakrishnam.kasiralfadesember.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.adapter.KasirAdapter;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.entity.Kasir;

import java.util.List;

public class KasirActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar pbKasir;
    private TextView tvPesan;
    private RecyclerView rvKasir;

    private KasirAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText etCari;

    private Button btnCari, btnRefresh;

    private KasirDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);

        pbKasir = findViewById(R.id.pb_kasir);
        tvPesan = findViewById(R.id.tv_pesan);
        rvKasir = findViewById(R.id.rv_kasir);
        etCari = findViewById(R.id.et_cari);
        btnCari = findViewById(R.id.btn_cari);
        btnRefresh = findViewById(R.id.btn_refresh);

        db = DatabaseClient.getInstance(getApplicationContext()).getKasirDatabase();

        loadKasir();

        btnCari.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCari){
            loadFindKasir();
        }else if(view == btnRefresh){
            loadKasir();
        }
    }

    private void loadFindKasir() {
        showLoading(true);

        final String search = etCari.getText().toString();

        if (!search.isEmpty()){
            new AsyncTask<Void, Void, List<Kasir>>(){
                @Override
                protected List<Kasir> doInBackground(Void... voids) {
                    List<Kasir> kasirs = db.kasirDao().findAllKasir("%"+search+"%");
                    return kasirs;
                }

                @Override
                protected void onPostExecute(List<Kasir> kasirs) {
                    if (kasirs != null && !kasirs.isEmpty()){
                        showMessage(false, "");
                    }else{
                        showMessage(true, "Data tidak ada");
                    }
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rvKasir.setLayoutManager(mLayoutManager);
                    mAdapter = new KasirAdapter(kasirs);
                    rvKasir.setAdapter(mAdapter);
                }
            }.execute();
        }else{
            Toast.makeText(getApplicationContext(), "Kolom pencarian kosong", Toast.LENGTH_SHORT).show();
            loadKasir();
        }

        showLoading(false);
    }

    private void loadKasir() {
        showLoading(true);

        new AsyncTask<Void, Void, List<Kasir>>(){
            @Override
            protected List<Kasir> doInBackground(Void... voids) {
                List<Kasir> kasirs = db.kasirDao().getAllKasir();
                return kasirs;
            }

            @Override
            protected void onPostExecute(List<Kasir> kasirs) {
                if (kasirs != null && !kasirs.isEmpty()){
                    showMessage(false, "");
                }else{
                    showMessage(true, "Data tidak ada");
                }
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rvKasir.setLayoutManager(mLayoutManager);
                mAdapter = new KasirAdapter(kasirs);
                rvKasir.setAdapter(mAdapter);
            }
        }.execute();

        showLoading(false);
    }


    private void showMessage(Boolean state, String message){
        if (state) {
            tvPesan.setText(message);
            tvPesan.setVisibility(View.VISIBLE);
        } else {
            tvPesan.setVisibility(View.GONE);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            pbKasir.setVisibility(View.VISIBLE);
        } else {
            pbKasir.setVisibility(View.GONE);
        }
    }


}
