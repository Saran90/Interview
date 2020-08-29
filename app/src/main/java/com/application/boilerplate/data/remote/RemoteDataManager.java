package com.application.boilerplate.data.remote;

import com.application.boilerplate.data.remote.login.IEmployeeRepository;

import javax.inject.Inject;

public class RemoteDataManager implements IRemoteDataManager{

    private ApiHeader apiHeader;
    private IEmployeeRepository employeeRepository;

    @Inject
    public RemoteDataManager(ApiHeader apiHeader, IEmployeeRepository employeeRepository) {
        this.apiHeader = apiHeader;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public IEmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    @Override
    public ApiHeader getApiHeader() {
        return apiHeader;
    }
}
