package com.divakrishnam.kasiralfadesember.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.adapter.KategoriAdapter;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.dialog.KategoriDialog;
import com.divakrishnam.kasiralfadesember.entity.Kategori;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class KategoriActivity extends AppCompatActivity implements KategoriDialog.KategoriDialogListener, KategoriAdapter.KategoriAdapterListener, View.OnClickListener {

    private ProgressBar pbKategori;
    private TextView tvPesan;
    private FloatingActionButton fabTambah;
    private RecyclerView rvKategori;

    private KategoriAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText etCari;

    private Button btnCari, btnRefresh;

    private KasirDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        getSupportActionBar().setTitle("Kategori");

        pbKategori = findViewById(R.id.pb_kategori);
        tvPesan = findViewById(R.id.tv_pesan);
        fabTambah = findViewById(R.id.fab_tambahkategori);
        rvKategori = findViewById(R.id.rv_kategori);
        btnCari = findViewById(R.id.btn_cari);
        btnRefresh = findViewById(R.id.btn_refresh);
        etCari = findViewById(R.id.et_cari);

        db = DatabaseClient.getInstance(getApplicationContext()).getKasirDatabase();

        loadKategori();

        btnCari.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);
        fabTambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCari){
            loadFindKategori();
        }else if (view == btnRefresh){
            loadKategori();
        }else if (view == fabTambah){
            showKategoriDialog();
        }
    }

    private void showKategoriDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        KategoriDialog kategoriDialog = KategoriDialog.newInstance("Tambah Barang");
        kategoriDialog.show(fragmentManager, "dialog_barang");
    }

    private void loadFindKategori() {
        showLoading(true);

        final String search = etCari.getText().toString();

        if (!search.isEmpty()){
            new AsyncTask<Void, Void, List<Kategori>>(){
                @Override
                protected List<Kategori> doInBackground(Void... voids) {
                    List<Kategori> kategoris = db.kategoriDao().findAllKategori("%"+search+"%");
                    return kategoris;
                }

                @Override
                protected void onPostExecute(List<Kategori> kategoris) {
                    if (kategoris != null && !kategoris.isEmpty()){
                        showMessage(false, "");
                    }else{
                        showMessage(true, "Data tidak ada");
                    }
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rvKategori.setLayoutManager(mLayoutManager);
                    mAdapter = new KategoriAdapter(getApplicationContext(), kategoris, KategoriActivity.this);
                    rvKategori.setAdapter(mAdapter);
                }
            }.execute();
        }else{
            Toast.makeText(getApplicationContext(), "Kolom pencarian kosong", Toast.LENGTH_SHORT).show();
            loadKategori();
        }

        showLoading(false);
    }

    private void loadKategori() {
        showLoading(true);

            new AsyncTask<Void, Void, List<Kategori>>(){
                @Override
                protected List<Kategori> doInBackground(Void... voids) {
                    List<Kategori> kategoris = db.kategoriDao().getAllKategori();
                    return kategoris;
                }

                @Override
                protected void onPostExecute(List<Kategori> kategoris) {
                    if (kategoris != null && !kategoris.isEmpty()){
                        showMessage(false, "");
                    }else{
                        showMessage(true, "Data tidak ada");
                    }
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    rvKategori.setLayoutManager(mLayoutManager);
                    mAdapter = new KategoriAdapter(getApplicationContext(), kategoris, KategoriActivity.this);
                    rvKategori.setAdapter(mAdapter);
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
            pbKategori.setVisibility(View.VISIBLE);
        } else {
            pbKategori.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFinishKategoriDialog() {
        loadKategori();
    }

    @Override
    public void onFinishKategoriAdapter() {
        loadKategori();
    }
}
