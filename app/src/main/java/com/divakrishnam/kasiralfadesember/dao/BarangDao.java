package com.divakrishnam.kasiralfadesember.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.divakrishnam.kasiralfadesember.entity.Barang;

@Dao
public interface BarangDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBarang(Barang barang);

}
