package com.divakrishnam.kasiralfadesember.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.entity.Kasir;

public class RegistrasiActivity extends AppCompatActivity implements View.OnClickListener {

    KasirDatabase db;

    private EditText etIdKasir, etNama, etUsername, etPassword, etCPassword;
    private Button btnRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        etIdKasir = findViewById(R.id.et_idkasir);
        etNama = findViewById(R.id.et_nama);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etCPassword = findViewById(R.id.et_cpassword);
        btnRegistrasi = findViewById(R.id.btn_registrasi);

        db = DatabaseClient.getInstance(getApplicationContext()).getKasirDatabase();

        btnRegistrasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegistrasi){
            registrasiKasir();
        }
    }

    private void registrasiKasir(){
        String idKasir = etIdKasir.getText().toString();
        String nama = etNama.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String cPassword = etCPassword.getText().toString();

        if (!idKasir.isEmpty() && !nama.isEmpty() && !username.isEmpty() && !password.isEmpty()){
            if (password.equals(cPassword)){
                Kasir kasir = new Kasir(idKasir, nama, username, password);
                registrasi(kasir);
            }else{
                Toast.makeText(getApplicationContext(), "Password harus sama", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Username dan Password harus diisi", Toast.LENGTH_LONG).show();
        }
    }

    private void registrasi(final Kasir kasir) {
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.kasirDao().registrasiKasir(kasir);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Log.d("status registrasi", ""+status);
                if(status > 0){
                    Toast.makeText(getApplicationContext(), "Data tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
