package com.divakrishnam.kasiralfadesember.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "kasir")
public class Kasir implements Serializable {

    @PrimaryKey
    @NonNull
    private String kasirId;
    private String kasirNama;
    private String kasirUsername;
    private String kasirPassword;

    @Ignore
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
