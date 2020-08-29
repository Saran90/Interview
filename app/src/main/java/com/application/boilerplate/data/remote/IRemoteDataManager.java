package com.application.boilerplate.data.remote;

import com.application.boilerplate.data.remote.login.IEmployeeRepository;

public interface IRemoteDataManager {

    IEmployeeRepository getEmployeeRepository();


    ApiHeader getApiHeader();
}
