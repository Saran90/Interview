package com.application.boilerplate.di.module;

import com.application.boilerplate.data.remote.login.EmployeeRepository;
import com.application.boilerplate.data.remote.login.EmployeeService;
import com.application.boilerplate.data.remote.login.IEmployeeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RemoteModule {

    @Provides
    @Singleton
    IEmployeeRepository provideAuthRepository(EmployeeRepository authRepository) {
        return authRepository;
    }

    @Provides
    @Singleton
    EmployeeService provideAuthService(Retrofit retrofit){
        return retrofit.create(EmployeeService.class);
    }
}
