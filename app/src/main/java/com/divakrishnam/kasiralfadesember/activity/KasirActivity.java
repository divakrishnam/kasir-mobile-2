package com.divakrishnam.kasiralfadesember.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.divakrishnam.kasiralfadesember.R;

public class KasirActivity extends AppCompatActivity {

    private ProgressBar pbKasir;
    private TextView tvPesan;
    private RecyclerView rvKasir;

    private KasirAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button btnCari, btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);
    }
}
