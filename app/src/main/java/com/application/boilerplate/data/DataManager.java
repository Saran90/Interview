package com.application.boilerplate.data;

import android.content.Context;

import com.application.boilerplate.data.local.db.AppDatabase;
import com.application.boilerplate.data.local.db.IDbManager;
import com.application.boilerplate.data.local.preference.IPreferenceManager;
import com.application.boilerplate.data.remote.RemoteDataManager;

import javax.inject.Inject;

public class DataManager implements IDataManager{

    private RemoteDataManager remoteDataManager;
    private IPreferenceManager preferenceManager;
    private Context context;
    private IDbManager dbManager;

    @Inject
    public DataManager(Context context, IDbManager dbManager, IPreferenceManager preferenceManager,
                          RemoteDataManager remoteDataManager) {
        this.context = context;
        this.dbManager = dbManager;
        this.preferenceManager = preferenceManager;
        this.remoteDataManager = remoteDataManager;
    }


    public RemoteDataManager getRemoteDataManager() {
        return remoteDataManager;
    }

    @Override
    public IDbManager geDbManager() {
        return dbManager;
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId) {

        getPreferenceManager().setAccessToken(accessToken);
        getPreferenceManager().setCurrentUserId(userId);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        getRemoteDataManager().getApiHeader().getProtectedApiHeader().setUserId(userId);
        getRemoteDataManager().getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    public IDbManager getDbManager() {
        return dbManager;
    }

    public IPreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

}
