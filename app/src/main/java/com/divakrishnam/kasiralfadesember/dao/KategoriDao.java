package com.divakrishnam.kasiralfadesember.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.divakrishnam.kasiralfadesember.entity.Kategori;

import java.util.List;

@Dao
public interface KategoriDao {

    @Query("SELECT * FROM kategori")
    List<Kategori> getAllKategori();

    @Query("SELECT * FROM kategori WHERE kategoriId LIKE :search OR kategoriNama LIKE :search")
    List<Kategori> findAllKategori(String search);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertKategori(Kategori kategori);

    @Update
    int updateKategori(Kategori kategori);

    @Delete
    int deleteKategori(Kategori kategori);
}
