package com.divakrishnam.kasiralfadesember.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.divakrishnam.kasiralfadesember.R;
import com.divakrishnam.kasiralfadesember.db.DatabaseClient;
import com.divakrishnam.kasiralfadesember.db.KasirDatabase;
import com.divakrishnam.kasiralfadesember.dialog.KategoriDialog;
import com.divakrishnam.kasiralfadesember.entity.Kategori;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>{

    private List<Kategori> mList;
    private Context mContext;
    private Activity mActivity;

    public KategoriAdapter(Context context, List<Kategori> list, Activity activity){
        mList = list;
        mContext = context;
        mActivity = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false);
        KategoriViewHolder mViewHolder = new KategoriViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, final int position) {

        final KasirDatabase db = DatabaseClient.getInstance(mContext).getKasirDatabase();

        holder.tvId.setText(" : "+mList.get(position).getKategoriId());
        holder.tvNama.setText(" : "+mList.get(position).getKategoriNama());
        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)mActivity).getSupportFragmentManager();
                KategoriDialog kategoriDialog = KategoriDialog.newInstance("Tambah Barang");
                Bundle bundle=new Bundle();
                bundle.putString("id", mList.get(position).getKategoriId());
                bundle.putString("nama", mList.get(position).getKategoriNama());
                kategoriDialog.setArguments(bundle);
                kategoriDialog.show(fragmentManager, "dialog_barang");
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Integer>(){
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        int status = db.kategoriDao().deleteKategori(mList.get(position));
                        return status;
                    }

                    @Override
                    protected void onPostExecute(Integer status) {
                        Log.d("status registrasi", ""+status);
                        if(status > 0){
                            Toast.makeText(mContext, "Data terhapus", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "Data gagal terhapus", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute();

                KategoriAdapterListener listener = (KategoriAdapterListener) mActivity;
                listener.onFinishKategoriAdapter();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId, tvNama;
        private Button btnUbah, btnHapus;

        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
        }
    }

    public interface KategoriAdapterListener {
        void onFinishKategoriAdapter();
    }

}
