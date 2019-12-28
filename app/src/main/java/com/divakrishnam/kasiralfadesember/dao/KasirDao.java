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

    @Query("SELECT * FROM Kasir WHERE KasirUsername = :username AND KasirPassword = :password LIMIT 1")
    Kasir loginKasir(String username, String password);

    @Query("SELECT * FROM Kasir")
    List<Kasir> getAllKasir();

}
