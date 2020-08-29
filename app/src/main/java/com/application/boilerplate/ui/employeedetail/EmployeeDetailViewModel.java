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

package com.application.boilerplate.ui.employeedetail;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.application.boilerplate.data.IDataManager;
import com.application.boilerplate.data.model.api.employee.Address;
import com.application.boilerplate.data.model.api.employee.Company;
import com.application.boilerplate.data.model.api.employee.EmployeeListResponse;
import com.application.boilerplate.data.model.api.employee.Geo;
import com.application.boilerplate.data.remote.rx.SchedulerProvider;
import com.application.boilerplate.ui.base.BaseViewModel;
import com.application.boilerplate.ui.employeelist.EmployeeModel;
import com.application.boilerplate.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class EmployeeDetailViewModel extends BaseViewModel<EmployeeDetailNavigator> {

    private final ObservableField<String> name;
    private final ObservableField<String> email;
    private final ObservableField<String> address;
    private final ObservableField<String> phone;
    private final ObservableField<String> website;
    private final ObservableField<String> company;
    private final ObservableField<String> username;
    private final ObservableField<String> profileImage;

    public EmployeeDetailViewModel(IDataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        name = new ObservableField<>("");
        email = new ObservableField<>("");
        address = new ObservableField<>("");
        phone = new ObservableField<>("");
        website = new ObservableField<>("");
        company = new ObservableField<>("");
        username = new ObservableField<>("");
        profileImage = new ObservableField<>("");
    }

    public void init(EmployeeModel employeeModel){
        name.set(employeeModel.getName());
        email.set(employeeModel.getEmail());
        address.set(employeeModel.getStreet()+", "+employeeModel.getSuite()+", "+employeeModel.getCity()+"\n"+employeeModel.getZipcode());
        phone.set(employeeModel.getName());
        website.set(employeeModel.getName());
        company.set(employeeModel.getName()+", "+employeeModel.getCompanyCatchPhrase()+", "+employeeModel.getCompanyBs());
        username.set(employeeModel.getName());
        profileImage.set(employeeModel.getProfileImage());
        getNavigator().loadProfileImage(profileImage.get());
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getAddress() {
        return address;
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public ObservableField<String> getWebsite() {
        return website;
    }

    public ObservableField<String> getCompany() {
        return company;
    }

    public ObservableField<String> getUsername() {
        return username;
    }

    public ObservableField<String> getProfileImage() {
        return profileImage;
    }

    public void onBackClicked(){
        getNavigator().navigateBack();
    }
}
