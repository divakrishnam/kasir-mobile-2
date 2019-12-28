package com.divakrishnam.kasiralfadesember.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.entity.Kasir;
import com.divakrishnam.kasiralfadesember.util.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    KasirDatabase db;

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegistrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegistrasi = findViewById(R.id.btn_registrasi);

        db = DatabaseClient.getInstance(getApplicationContext()).getKasirDatabase();

        btnLogin.setOnClickListener(this);
        btnRegistrasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            loginKasir();
        }else if (view == btnRegistrasi){
            startActivity(new Intent(getApplicationContext(), RegistrasiActivity.class));
        }
    }

    private void loginKasir() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()){
            login(username, password);
        }else{
            Toast.makeText(getApplicationContext(), "Username dan Password harus diisi", Toast.LENGTH_LONG).show();
        }
    }

    private void login(final String username, final String password) {
        new AsyncTask<Void, Void, Kasir>(){
            @Override
            protected Kasir doInBackground(Void... voids) {
                Kasir kasir = db.kasirDao().loginKasir(username, password);
                return kasir;
            }

            @Override
            protected void onPostExecute(Kasir kasir) {
                if (kasir != null){
                    Toast.makeText(getApplicationContext(), "Berhasil login", Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getApplicationContext()).kasirLogin(kasir);
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Username dan Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


}
