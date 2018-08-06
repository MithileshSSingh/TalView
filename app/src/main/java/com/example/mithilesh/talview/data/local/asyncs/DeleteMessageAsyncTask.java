package com.example.mithilesh.talview.data.local.asyncs;

import android.os.AsyncTask;

import com.example.mithilesh.talview.data.local.dao.MessageDao;

public class DeleteMessageAsyncTask extends AsyncTask<Void, Void, Void> {

    private MessageDao mAsyncTaskDao;

    public DeleteMessageAsyncTask(MessageDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        synchronized (DeleteMessageAsyncTask.class) {

            try {
                mAsyncTaskDao.deleteAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}