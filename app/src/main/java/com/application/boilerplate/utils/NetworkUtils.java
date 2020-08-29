package com.application.boilerplate.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.HttpException;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class NetworkUtils {

    public static final int NETWORK_READ_TIME_OUT = 30;
    public static final int NETWORK_CONNECT_TIME_OUT = 30;
    public static final int NETWORK_WRITE_TIME_OUT = 30;
    public static final int RESPONSE_OK = 200;
    public static final int RESPONSE_ERROR_START = 399;
    public static final int RESPONSE_ERROR_END = 500;
    public static final int RESPONSE_INTERNAL_SERVER_ERROR_START = 499;
    public static final int RESPONSE_INTERNAL_SERVER_ERROR_END = 600;
    public static final int RESPONSE_UNAUTHORIZED = 401;
    public static final int RESPONSE_NODATA = 204;
    public static final String NETWORK_AUTHORIZATION = "Authorization";
    public static final String NETWORK_CONTENT_TYPE = "Content-Type";
    public static final String NETWORK_CONTENT_TYPE_JSON = "application/json";
    public static final String NETWORK_CONTENT_TYPE_MULTIPART_FORM_DATA = "multipart/form-data";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return throwable instanceof HttpException
                && ((HttpException) throwable).code() == statusCode;
    }
}
