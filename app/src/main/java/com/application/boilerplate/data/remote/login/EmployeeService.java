package com.application.boilerplate.data.remote.login;

import com.application.boilerplate.data.model.api.employee.EmployeeListResponse;
import com.application.boilerplate.data.remote.ApiEndPoint;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.application.boilerplate.utils.NetworkUtils.NETWORK_AUTHORIZATION;

public interface EmployeeService {

    @GET(ApiEndPoint.ENDPOINT_EMPLOYEE_LIST)
    Single<List<EmployeeListResponse>> getEmployeeList();

}
