package com.eesaaphilips.interviewproject1.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.eesaaphilips.interviewproject1.model.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "ProductDatabase.db";
    private static volatile ProductDatabase instance;
    private static RoomDatabase.Callback sRoomDataCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    /**
     * Constructor: creates new database if an instance of the database does not exist
     *
     * @param context context used to build a new room database
     * @return
     */
    public static ProductDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ProductDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, DATABASE_NAME).addCallback(sRoomDataCallback).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    /**
     * Destroys the database instance
     */
    public static void destroyInstance() {
        instance = null;
    }

    public abstract ProductDao productDao();
}
