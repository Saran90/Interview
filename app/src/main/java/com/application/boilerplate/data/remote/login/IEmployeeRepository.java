package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.employee.EmployeeListResponse;

import java.util.List;

import io.reactivex.Single;

public interface IEmployeeRepository {
    Single<List<EmployeeListResponse>> getEmployeeList();

    EmployeeService getEmployeeService();
}
