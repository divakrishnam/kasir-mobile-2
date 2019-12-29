package com.divakrishnam.kasiralfadesember.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.divakrishnam.kasiralfadesember.entity.Kasir;

import java.util.List;

@Dao
public interface KasirDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long registrasiKasir(Kasir kasir);

    @Query("SELECT * FROM kasir WHERE kasirUsername = :username AND kasirPassword = :password LIMIT 1")
    Kasir loginKasir(String username, String password);

    @Query("SELECT * FROM kasir")
    List<Kasir> getAllKasir();

    @Query("SELECT * FROM kasir WHERE kasirId LIKE :search OR kasirNama LIKE :search")
    List<Kasir> findAllKasir(String search);

}
