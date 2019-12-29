package com.divakrishnam.kasiralfadesember.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "kategori")
public class Kategori implements Serializable, Parcelable {

    @PrimaryKey
    @NonNull
    private String kategoriId;
    private String kategoriNama;

    public Kategori(@NonNull String kategoriId, String kategoriNama) {
        this.kategoriId = kategoriId;
        this.kategoriNama = kategoriNama;
    }

    @NonNull
    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(@NonNull String kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKategoriNama() {
        return kategoriNama;
    }

    public void setKategoriNama(String kategoriNama) {
        this.kategoriNama = kategoriNama;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kategoriId);
        dest.writeString(this.kategoriNama);
    }

    protected Kategori(Parcel in) {
        this.kategoriId = in.readString();
        this.kategoriNama = in.readString();
    }

    public static final Parcelable.Creator<Kategori> CREATOR = new Parcelable.Creator<Kategori>() {
        @Override
        public Kategori createFromParcel(Parcel source) {
            return new Kategori(source);
        }

        @Override
        public Kategori[] newArray(int size) {
            return new Kategori[size];
        }
    };
}
