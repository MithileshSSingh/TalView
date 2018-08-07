package com.example.mithilesh.talview.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mithilesh.talview.data.local.dao.PostDao;
import com.example.mithilesh.talview.data.local.entities.Post;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class DbHelper extends RoomDatabase {

    private static DbHelper INSTANCE;

    public static DbHelper getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (DbHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbHelper.class, "talview_db").build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract PostDao postDao();
}
