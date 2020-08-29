package com.application.boilerplate.data.local.preference;

/**
 * Created by SARAN M S on 03/03/20.
 */

public interface IPreferenceManager {

    String getToken();

    void setToken(String token);

    String getAccessToken();

    void setAccessToken(String token);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    void clearAll();

}

