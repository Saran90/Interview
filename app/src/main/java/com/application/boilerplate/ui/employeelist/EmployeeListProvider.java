package com.application.boilerplate.ui.employeelist;

import android.app.Activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Saran M S on 8/17/2019.
 */
@Module
public abstract class EmployeeListProvider {
    @ContributesAndroidInjector(modules = EmployeeListModule.class)
    abstract Activity provideEmployeeListActivityFactory();

}
