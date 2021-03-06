package com.example.my_second_app.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.entities.enums.Converters;

@Database(entities = {PackShow.class},version=1 )
@TypeConverters({Converters.class})
public abstract class PackDatabase extends RoomDatabase {
    private static PackDatabase instance;

    public abstract PackDao packDao();

    public static synchronized PackDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PackDatabase.class, "Pack_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PackDao packDao;

        private PopulateDbAsyncTask(PackDatabase db) {
            packDao= db.packDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            packDao.insert(new PackShow(PackType.BIGֹ_PACKAGE,
//                                        PackWeight.BETWEEN_0g_TO_500g,
//                                        true,
//                                        PackStatus.OFFER_TO_COLLECT,
//                                        "NO","Address","1234"));
//            packDao.insert(new Pack());
//            packDao.insert(new Pack());
            return null;
        }
    }
}
