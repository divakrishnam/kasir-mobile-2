package com.divakrishnam.kasiralfadesember.db;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context mContext;
    private static DatabaseClient mInstance;

    private KasirDatabase kasirDatabase;

    private DatabaseClient(Context context) {
        mContext = context;

        kasirDatabase = Room.databaseBuilder(mContext, KasirDatabase.class, "AlfaDesemberDB.db").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public KasirDatabase getKasirDatabase() {
        return kasirDatabase;
    }

}
