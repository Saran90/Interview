package com.application.boilerplate.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.application.boilerplate.di.PreferenceInfo;
import com.application.boilerplate.utils.AppConstants;

import javax.inject.Inject;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class PreferenceManager implements IPreferenceManager {

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_AUTH_TOKEN = "PREF_AUTH_TOKEN";
    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";

    SharedPreferences preferences;

    @Inject
    public PreferenceManager(Context context, @PreferenceInfo String prefFileName) {
        preferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getToken() {
        return preferences.getString(PREF_AUTH_TOKEN, "");
    }

    @Override
    public void setToken(String token) {
        preferences.edit().putString(PREF_AUTH_TOKEN, token).apply();
    }

    @Override
    public String getAccessToken() {
        return preferences.getString(PREF_ACCESS_TOKEN,"");
    }

    @Override
    public void setAccessToken(String token) {
        preferences.edit().putString(PREF_ACCESS_TOKEN,token).apply();
    }

    @Override
    public Long getCurrentUserId() {
        long userId = preferences.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        preferences.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public void clearAll() {
        preferences.edit().clear().apply();
    }
}
