package com.divakrishnam.kasiralfadesember.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Kasir")
public class Kasir implements Serializable {

    @PrimaryKey
    @NonNull
    private String kasirId;

    @ColumnInfo(name = "KasirNama")
    private String kasirNama;

    @ColumnInfo(name = "KasirUsername")
    private String kasirUsername;

    @ColumnInfo(name = "KasirPassword")
    private String kasirPassword;

    public Kasir() {
    }

    public Kasir(@NonNull String kasirId, String kasirNama, String kasirUsername, String kasirPassword) {
        this.kasirId = kasirId;
        this.kasirNama = kasirNama;
        this.kasirUsername = kasirUsername;
        this.kasirPassword = kasirPassword;
    }

    public String getKasirId() {
        return kasirId;
    }

    public void setKasirId(String kasirId) {
        this.kasirId = kasirId;
    }

    public String getKasirNama() {
        return kasirNama;
    }

    public void setKasirNama(String kasirNama) {
        this.kasirNama = kasirNama;
    }

    public String getKasirUsername() {
        return kasirUsername;
    }

    public void setKasirUsername(String kasirUsername) {
        this.kasirUsername = kasirUsername;
    }

    public String getKasirPassword() {
        return kasirPassword;
    }

    public void setKasirPassword(String kasirPassword) {
        this.kasirPassword = kasirPassword;
    }
}
