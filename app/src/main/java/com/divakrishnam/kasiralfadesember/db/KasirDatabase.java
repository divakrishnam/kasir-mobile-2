package com.divakrishnam.kasiralfadesember.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.divakrishnam.kasiralfadesember.dao.BarangDao;
import com.divakrishnam.kasiralfadesember.dao.KasirDao;
import com.divakrishnam.kasiralfadesember.entity.Barang;
import com.divakrishnam.kasiralfadesember.entity.Kasir;

@Database(entities = {Barang.class, Kasir.class}, version = 1)
public abstract class KasirDatabase extends RoomDatabase {

    public abstract BarangDao barangDao();

    public abstract KasirDao kasirDao();

}
