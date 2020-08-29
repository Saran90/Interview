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

import io.reactivex.Observable;

/**
 * Created by SARAN M S on 03/03/20.
 */

public interface IDbManager {

    Observable<List<Employee>> getAllEmployees();

    Observable<Boolean> insertEmployee(final Employee employee);

    Observable<Boolean> insertAllEmployee(final List<Employee> employees);

    Observable<Boolean> deleteAll();

}
