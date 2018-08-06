package com.example.mithilesh.talview.di;

import android.content.Context;

import com.example.mithilesh.talview.data.Repository;
import com.example.mithilesh.talview.data.local.LocalDataSource;
import com.example.mithilesh.talview.data.remote.RemoteDataSource;

public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(LocalDataSource.getInstance(context), RemoteDataSource.getInstance(context));
    }
}
