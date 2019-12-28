package com.divakrishnam.kasiralfadesember.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Barang")
public class Barang implements Serializable {

    @PrimaryKey
    @NonNull
    private String barangId;

    @ColumnInfo(name = "BarangNama")
    private String barangNama;

    @ColumnInfo(name = "BarangIdKategori")
    private String barangIdKategori;

    @ColumnInfo(name = "BarangStok")
    private String barangStok;

    @ColumnInfo(name = "BarangHarga")
    private String barangHarga;

    public String getBarangId() {
        return barangId;
    }

    public void setBarangId(String barangId) {
        this.barangId = barangId;
    }

    public String getBarangNama() {
        return barangNama;
    }

    public void setBarangNama(String barangNama) {
        this.barangNama = barangNama;
    }

    public String getBarangIdKategori() {
        return barangIdKategori;
    }

    public void setBarangIdKategori(String barangIdKategori) {
        this.barangIdKategori = barangIdKategori;
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
