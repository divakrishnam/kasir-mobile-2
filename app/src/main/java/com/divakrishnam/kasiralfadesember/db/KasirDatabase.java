package com.divakrishnam.kasiralfadesember.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.divakrishnam.kasiralfadesember.dao.BarangDao;
import com.divakrishnam.kasiralfadesember.dao.DetailTransaksiDao;
import com.divakrishnam.kasiralfadesember.dao.KasirDao;
import com.divakrishnam.kasiralfadesember.dao.KategoriDao;
import com.divakrishnam.kasiralfadesember.dao.TransaksiDao;
import com.divakrishnam.kasiralfadesember.entity.Barang;
import com.divakrishnam.kasiralfadesember.entity.Kasir;
import com.divakrishnam.kasiralfadesember.entity.Kategori;

@Database(entities = {Kasir.class, Kategori.class, Barang.class}, version = 1)
public abstract class KasirDatabase extends RoomDatabase {

    public abstract BarangDao barangDao();

    public abstract KategoriDao kategoriDao();

    public abstract KasirDao kasirDao();

}
