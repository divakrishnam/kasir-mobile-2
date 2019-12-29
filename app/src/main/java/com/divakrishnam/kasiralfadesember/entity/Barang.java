package com.divakrishnam.kasiralfadesember.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "barang", foreignKeys = @ForeignKey(entity = Kategori.class,
        parentColumns = "kategoriId",
        childColumns = "barangKategori",
        onDelete = CASCADE))
public class Barang implements Serializable {

    @PrimaryKey
    @NonNull
    private String barangId;
    private String barangNama;
    @ColumnInfo(index = true)
    private String barangKategori;
    private String barangStok;
    private String barangHarga;

    public Barang(@NonNull String barangId, String barangNama, String barangKategori, String barangStok, String barangHarga) {
        this.barangId = barangId;
        this.barangNama = barangNama;
        this.barangKategori = barangKategori;
        this.barangStok = barangStok;
        this.barangHarga = barangHarga;
    }

    @NonNull
    public String getBarangId() {
        return barangId;
    }

    public void setBarangId(@NonNull String barangId) {
        this.barangId = barangId;
    }

    public String getBarangNama() {
        return barangNama;
    }

    public void setBarangNama(String barangNama) {
        this.barangNama = barangNama;
    }

    public String getBarangKategori() {
        return barangKategori;
    }

    public void setBarangKategori(String barangKategori) {
        this.barangKategori = barangKategori;
    }

    public String getBarangStok() {
        return barangStok;
    }

    public void setBarangStok(String barangStok) {
        this.barangStok = barangStok;
    }

    public String getBarangHarga() {
        return barangHarga;
    }

    public void setBarangHarga(String barangHarga) {
        this.barangHarga = barangHarga;
    }
}
