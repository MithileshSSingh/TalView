package com.example.mithilesh.talview.data.local;

import android.content.Context;

import com.example.mithilesh.talview.data.DataSource;

public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE = null;
    private static DbHelper mDbHelper;

    private Context mContext;

    private LocalDataSource() {

    }

    private LocalDataSource(Context context) {
        mContext = context;
        mDbHelper = DbHelper.getInstance(context);
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource(context);
                }
            }
        }

        return INSTANCE;
    }
}
