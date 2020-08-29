package com.application.boilerplate.di.builder;

import com.application.boilerplate.ui.employeedetail.EmployeeDetailActivity;
import com.application.boilerplate.ui.employeelist.EmployeeListActivity;
import com.application.boilerplate.ui.employeelist.EmployeeListModule;
import com.application.boilerplate.ui.employeelist.EmployeeListProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by SARAN M S on 03/03/20.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            EmployeeListModule.class,
            EmployeeListProvider.class})
    abstract EmployeeListActivity bindEmployeeListActivity();

    @ContributesAndroidInjector()
    abstract EmployeeDetailActivity bindEmployeeDetailActivity();

}
