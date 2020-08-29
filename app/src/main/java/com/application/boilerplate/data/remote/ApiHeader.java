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

package com.application.boilerplate.data.remote;

import com.application.boilerplate.di.ApiInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by SARAN M S on 03/03/20.
 */

@Singleton
public class ApiHeader {

    private ProtectedApiHeader mProtectedApiHeader;

    private PublicApiHeader mPublicApiHeader;

    private AuthApiHeader mAuthApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader,
                     AuthApiHeader authApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
        mAuthApiHeader = authApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public AuthApiHeader getAuthApiHeader() {
        return mAuthApiHeader;
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("Authorization")
        private String mAccessToken;

        @Expose
        @SerializedName("user_id")
        private Long mUserId;

        public ProtectedApiHeader(Long mUserId, String mAccessToken) {
            this.mUserId = mUserId;
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }

        public Long getUserId() {
            return mUserId;
        }

        public void setUserId(Long mUserId) {
            this.mUserId = mUserId;
        }
    }

    public static final class PublicApiHeader {

        @Inject
        public PublicApiHeader() {

        }

    }

    public static final class AuthApiHeader {

        @Expose
        @SerializedName("Authorization")
        private String mAccessToken;

        public AuthApiHeader(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }
    }

}
