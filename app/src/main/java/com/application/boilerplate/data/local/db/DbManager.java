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

package com.application.boilerplate.data.local.db;

import com.application.boilerplate.data.model.db.Employee;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by SARAN M S on 03/03/20.
 */

@Singleton
public class DbManager implements IDbManager {

    private final AppDatabase mAppDatabase;

    @Inject
    public DbManager(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<Employee>> getAllEmployees() {
        return Observable.fromCallable(new Callable<List<Employee>>() {
            @Override
            public List<Employee> call() throws Exception {
                return mAppDatabase.employeeDao().loadAll();
            }
        });

    }

    @Override
    public Observable<Boolean> insertEmployee(Employee employee) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.employeeDao().insert(employee);
                return true;
            }
        });

    }

    @Override
    public Observable<Boolean> insertAllEmployee(List<Employee> employees) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.employeeDao().insertAll(employees);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteAll() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.employeeDao().deleteAll();
                return true;
            }
        });

    }
}
