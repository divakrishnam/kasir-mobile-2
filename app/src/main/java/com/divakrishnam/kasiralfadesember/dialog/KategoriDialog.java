package com.divakrishnam.kasiralfadesember.dialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.entity.Kategori;

public class KategoriDialog extends DialogFragment implements View.OnClickListener {

    EditText etId, etNama;
    Button btnSimpan, btnBatal;

    KasirDatabase db;

    String mId, mNama;

    public KategoriDialog() {

    }

    public static KategoriDialog newInstance(String title) {
        KategoriDialog frag = new KategoriDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_kategori, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etId = view.findViewById(R.id.et_id);
        etNama = view.findViewById(R.id.et_nama);

        btnSimpan = view.findViewById(R.id.btn_simpan);
        btnBatal = view.findViewById(R.id.btn_batal);

        db = DatabaseClient.getInstance(getContext()).getKasirDatabase();

        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);

        mId = getArguments().getString("id");
        mNama = getArguments().getString("nama");

        if (mNama != null && mId != null) {
            etId.setText(mId);
            etNama.setText(mNama);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnSimpan) {
            simpanKategori();
            getDialog().dismiss();
        } else if (view == btnBatal) {
            getDialog().dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        String title = getArguments().getString("title", "Judul");
        getDialog().setTitle(title);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    private void simpanKategori() {
        String id = etId.getText().toString();
        String nama = etNama.getText().toString();

        if (!id.isEmpty() && !nama.isEmpty()) {
            Kategori kategori = new Kategori(id, nama);
            if (mNama != null && mId != null) {
                ubah(kategori);
            } else {
                simpan(kategori);
            }
        } else {
            Toast.makeText(getContext(), "Kolom belum terisi", Toast.LENGTH_LONG).show();
        }
    }

    private void ubah(final Kategori kategori){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                int status = db.kategoriDao().updateKategori(kategori);
                return status;
            }

            @Override
            protected void onPostExecute(Integer status) {
                if (status > 0) {
                    Toast.makeText(getContext(), "Data tersimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

        KategoriDialogListener listener = (KategoriDialogListener) getActivity();
        listener.onFinishKategoriDialog();
    }

    private void simpan(final Kategori kategori) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.kategoriDao().insertKategori(kategori);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                if (status > 0) {
                    Toast.makeText(getContext(), "Data tersimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

        KategoriDialogListener listener = (KategoriDialogListener) getActivity();
        listener.onFinishKategoriDialog();
    }

    public interface KategoriDialogListener {
        void onFinishKategoriDialog();
    }
}
