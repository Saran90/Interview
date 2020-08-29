package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.employee.EmployeeListResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class EmployeeRepository implements IEmployeeRepository {

    private EmployeeService mEmployeeService;

    @Inject
    public EmployeeRepository(EmployeeService employeeService) {
        this.mEmployeeService = employeeService;
    }

    @Override
    public Single<List<EmployeeListResponse>> getEmployeeList() {
        return mEmployeeService.getEmployeeList();
    }

    @Override
    public EmployeeService getEmployeeService() {
        return mEmployeeService;
    }
}
