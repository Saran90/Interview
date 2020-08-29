/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.application.boilerplate.di.module;

import android.app.Application;
import android.content.Context;

import com.application.boilerplate.BuildConfig;
import com.application.boilerplate.data.DataManager;
import com.application.boilerplate.data.IDataManager;
import com.application.boilerplate.data.local.db.AppDatabase;
import com.application.boilerplate.data.local.db.DbManager;
import com.application.boilerplate.data.local.db.IDbManager;
import com.application.boilerplate.data.local.preference.IPreferenceManager;
import com.application.boilerplate.data.local.preference.PreferenceManager;
import com.application.boilerplate.data.remote.ApiHeader;
import com.application.boilerplate.data.remote.IRemoteDataManager;
import com.application.boilerplate.data.remote.RemoteDataManager;
import com.application.boilerplate.data.remote.rx.AppSchedulerProvider;
import com.application.boilerplate.data.remote.rx.SchedulerProvider;
import com.application.boilerplate.di.ApiInfo;
import com.application.boilerplate.di.DatabaseInfo;
import com.application.boilerplate.di.PreferenceInfo;
import com.application.boilerplate.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import com.application.boilerplate.R;

import java.util.concurrent.TimeUnit;

import static com.application.boilerplate.utils.NetworkUtils.NETWORK_CONNECT_TIME_OUT;
import static com.application.boilerplate.utils.NetworkUtils.NETWORK_READ_TIME_OUT;
import static com.application.boilerplate.utils.NetworkUtils.NETWORK_WRITE_TIME_OUT;

/**
 * Created by SARAN M S on 03/03/20.
 */
@Module(
        includes = RemoteModule.class
)
public class AppModule {

    @Provides
    @Singleton
    IRemoteDataManager provideRemoteDataManager(RemoteDataManager appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    IDbManager provideDbManager(DbManager dbManager) {
        return dbManager;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    IPreferenceManager providePreferencesHelper(PreferenceManager appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferenceManager preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getToken());
    }

    @Provides
    @Singleton
    ApiHeader.AuthApiHeader provideAuthApiHeader(PreferenceManager preferencesHelper) {
        return new ApiHeader.AuthApiHeader(
                preferencesHelper.getToken());
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(NETWORK_READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(NETWORK_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
