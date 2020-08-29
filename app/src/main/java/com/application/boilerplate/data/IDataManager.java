package com.application.boilerplate.data;

import com.application.boilerplate.data.local.db.IDbManager;
import com.application.boilerplate.data.remote.IRemoteDataManager;

public interface IDataManager {

    void updateUserInfo(
            String accessToken,
            Long userId);

    void updateApiHeader(Long userId, String accessToken);

    IRemoteDataManager getRemoteDataManager();
    IDbManager geDbManager();
}
