package com.firsteconomy.nytapp.db;

import android.arch.persistence.room.Room;
import android.content.Context;

@SuppressWarnings("ALL")
public class DBManager {
    private static DBManager dbManager;
    private NyttAppDb database;
    private final String dbName = "nytts_app_user_database";

    public static DBManager getDbManager() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public NyttAppDb getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, NyttAppDb.class, dbName).build();
        }
        return database;
    }
}
