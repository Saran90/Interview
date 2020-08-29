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

package com.application.boilerplate.ui.employeelist;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.application.boilerplate.data.IDataManager;
import com.application.boilerplate.data.local.db.AppDatabase;
import com.application.boilerplate.data.model.api.employee.Address;
import com.application.boilerplate.data.model.api.employee.Company;
import com.application.boilerplate.data.model.api.employee.EmployeeListResponse;
import com.application.boilerplate.data.model.api.employee.Geo;
import com.application.boilerplate.data.model.db.Employee;
import com.application.boilerplate.data.remote.rx.SchedulerProvider;
import com.application.boilerplate.ui.base.BaseViewModel;
import com.application.boilerplate.utils.CommonUtils;
import com.application.boilerplate.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import okhttp3.Credentials;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class EmployeeListViewModel extends BaseViewModel<EmployeeListNavigator> {

    private final ObservableField<Boolean> isEmployeeAvailable;
    private MutableLiveData<List<EmployeeModel>> employeeListMutableLiveData;
    private MutableLiveData<List<EmployeeModel>> searchedEmployeeListMutableLiveData;
    private ObservableField<String> searchTextObservableField;
    private final ObservableField<Boolean> isSearchClearViewEnabled;

    public EmployeeListViewModel(IDataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        isEmployeeAvailable = new ObservableField<>(true);
        searchTextObservableField = new ObservableField<>();
        employeeListMutableLiveData = new MutableLiveData<>();
        employeeListMutableLiveData.setValue(new ArrayList<>());
        searchedEmployeeListMutableLiveData = new MutableLiveData<>();
        isSearchClearViewEnabled = new ObservableField<>(false);
        fetchEmployees();
    }

    private void fetchEmployees() {
        getCompositeDisposable().add(getDataManager().geDbManager().getAllEmployees()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    if (response.size()>0){
                        List<EmployeeModel> employeeModels = convertFromDbModelToEmployeeeModel(response);
                        employeeListMutableLiveData.setValue(employeeModels);
                        searchedEmployeeListMutableLiveData.setValue(employeeModels);
                        searchTextObservableField.set("");
                        isEmployeeAvailable.set(true);
                    }else {
                        fetchPatientListFromServer();
                    }
                }, throwable -> {
                    setIsLoading(false);
                    handleError(throwable);
                    fetchPatientListFromServer();
                }));

    }

    public ObservableField<Boolean> getIsSearchClearViewEnabled() {
        return isSearchClearViewEnabled;
    }

    public ObservableField<String> getSearchTextObservableField() {
        return searchTextObservableField;
    }

    public MutableLiveData<List<EmployeeModel>> getEmployeeListMutableLiveData() {
        return employeeListMutableLiveData;
    }

    public MutableLiveData<List<EmployeeModel>> getSearchedEmployeeListMutableLiveData() {
        return searchedEmployeeListMutableLiveData;
    }

    public ObservableField<Boolean> getIsEmployeeAvailable() {
        return isEmployeeAvailable;
    }

    public void fetchPatientListFromServer() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().getRemoteDataManager()
                .getEmployeeRepository().getEmployeeList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    convertResponseToEmployeeModelList(response);
                }, throwable -> {
                    setIsLoading(false);
                    isEmployeeAvailable.set(false);
                    handleError(throwable);
                }));
    }

    private void convertResponseToEmployeeModelList(List<EmployeeListResponse> response) {
        List<EmployeeModel> employeeModels = new ArrayList<>();
        if (response!=null){
            if (response.size()>0){
                for (int i = 0;i<response.size();i++){
                    EmployeeModel employeeModel = new EmployeeModel();
                    EmployeeListResponse employeeListResponse = response.get(i);
                    employeeModel.setId(employeeListResponse.getId());
                    employeeModel.setName(employeeListResponse.getName());
                    employeeModel.setUsername(employeeListResponse.getUsername());
                    employeeModel.setPhone(employeeListResponse.getPhone());
                    employeeModel.setEmail(employeeListResponse.getEmail());
                    employeeModel.setProfileImage(employeeListResponse.getProfileImage());
                    employeeModel.setWebsite(employeeListResponse.getWebsite());
                    Company company = employeeListResponse.getCompany();
                    employeeModel.setCompanyName(company!=null?company.getName():"");
                    employeeModel.setCompanyCatchPhrase(company!=null?company.getCatchPhrase():"");
                    employeeModel.setCompanyBs(company!=null?company.getBs():"");
                    Address address = employeeListResponse.getAddress();
                    employeeModel.setStreet(address!=null?address.getStreet():"");
                    employeeModel.setSuite(address!=null?address.getSuite():"");
                    employeeModel.setCity(address!=null?address.getCity():"");
                    employeeModel.setZipcode(address!=null?address.getZipcode():"");
                    if (address!=null){
                        Geo geo = address.getGeo();
                        employeeModel.setLatitude(geo.getLat());
                        employeeModel.setLongitude(geo.getLng());
                    }else {
                        employeeModel.setLatitude("");
                        employeeModel.setLongitude("");
                    }
                    employeeModels.add(employeeModel);
                }
                employeeListMutableLiveData.setValue(employeeModels);
                searchedEmployeeListMutableLiveData.setValue(employeeModels);
                searchTextObservableField.set("");
                isEmployeeAvailable.set(true);
                saveDataToDb(employeeModels);
            }else {
                isEmployeeAvailable.set(false);
                getNavigator().noEmployeesAvailable();
            }
        }else {
            isEmployeeAvailable.set(false);
            getNavigator().noEmployeesAvailable();
        }
    }

    private void saveDataToDb(List<EmployeeModel> employeeModels) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0;i<employeeModels.size();i++){
            EmployeeModel employeeModel = employeeModels.get(i);
            Employee employee = new Employee();
            employee.employeeId = employeeModel.getId();
            employee.name = employeeModel.getName();
            employee.email = employeeModel.getEmail();
            employee.phone = employeeModel.getPhone();
            employee.city = employeeModel.getCity();
            employee.companyBs = employeeModel.getCompanyBs();
            employee.companyCatchPhrase = employeeModel.getCompanyCatchPhrase();
            employee.latitude = employeeModel.getLatitude();
            employee.longitude = employeeModel.getLongitude();
            employee.profileImage = employeeModel.getProfileImage();
            employee.street = employeeModel.getStreet();
            employee.companyName = employeeModel.getCompanyName();
            employees.add(employee);
        }
        getCompositeDisposable().add(getDataManager().geDbManager().deleteAll()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    if (response){
                        saveData(employees);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    isEmployeeAvailable.set(false);
                    handleError(throwable);
                }));

    }

    private void saveData(List<Employee> employees) {
        getCompositeDisposable().add(getDataManager().geDbManager().insertAllEmployee(employees)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    if (response){
                        setIsLoading(false);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    isEmployeeAvailable.set(false);
                    handleError(throwable);
                }));

    }

    private List<EmployeeModel> convertFromDbModelToEmployeeeModel(List<Employee> employees){
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (int i = 0;i<employees.size();i++){
            EmployeeModel employeeModel = new EmployeeModel();
            Employee employee = employees.get(i);
            employeeModel.setLongitude(employee.longitude);
            employeeModel.setLongitude(employee.longitude);
            employeeModel.setZipcode(employee.zipcode);
            employeeModel.setCity(employee.city);
            employeeModel.setSuite(employee.suite);
            employeeModel.setStreet(employee.street);
            employeeModel.setCompanyBs(employee.companyBs);
            employeeModel.setCompanyCatchPhrase(employee.companyCatchPhrase);
            employeeModel.setCompanyName(employee.companyName);
            employeeModel.setWebsite(employee.website);
            employeeModel.setEmail(employee.email);
            employeeModel.setPhone(employee.phone);
            employeeModel.setUsername(employee.username);
            employeeModel.setName(employee.name);
            employeeModel.setId(employee.employeeId);
            employeeModel.setProfileImage(employee.profileImage);
            employeeModels.add(employeeModel);
        }
        return employeeModels;
    }

    public void handleError(Throwable throwable){
        if (NetworkUtils.isHttpStatusCode(throwable, 400)
                || NetworkUtils.isHttpStatusCode(throwable, 400)) {
            ResponseBody body = ((HttpException) throwable).response().errorBody();
            try {
                getNavigator().showMessage(body.string());
            } catch (IOException e1) {
                Log.d(EmployeeListViewModel.class.getName(),e1.getMessage());
            } finally {
                if (body != null) {
                    body.close();
                }
            }
        }
    }

    public void onCloseButtonClicked(){
        searchTextObservableField.set("");
        getNavigator().hideKeyboard();
    }

    public void onSearchTextChanged(String searchText){
        if (searchText.equalsIgnoreCase("")){
            searchedEmployeeListMutableLiveData.setValue(employeeListMutableLiveData.getValue());
            isSearchClearViewEnabled.set(false);
            getNavigator().hideKeyboard();
        }else {
            isSearchClearViewEnabled.set(true);
            searchedEmployeeListMutableLiveData.setValue(new ArrayList<>());
            List<EmployeeModel> employeeModels = new ArrayList<>();
            for (int i = 0;i<employeeListMutableLiveData.getValue().size();i++){
                if (employeeListMutableLiveData.getValue().get(i).getName().toLowerCase().contains(searchText.toLowerCase())){
                    employeeModels.add(employeeListMutableLiveData.getValue().get(i));
                }else if (employeeListMutableLiveData.getValue().get(i).getEmail().toLowerCase().contains(searchText.toLowerCase())){
                    employeeModels.add(employeeListMutableLiveData.getValue().get(i));
                }
            }
            searchedEmployeeListMutableLiveData.setValue(employeeModels);
        }
    }

}
