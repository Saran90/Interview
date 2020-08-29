package com.application.boilerplate;

import com.application.boilerplate.data.DataManager;
import com.application.boilerplate.data.remote.rx.SchedulerProvider;
import com.application.boilerplate.ui.employeedetail.EmployeeDetailViewModel;
import com.application.boilerplate.ui.employeelist.EmployeeListViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by SARAN M S on 03/03/20.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final DataManager dataManager;
  private final SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(EmployeeListViewModel.class)) {
      //noinspection unchecked
      return (T) new EmployeeListViewModel(dataManager,schedulerProvider);
    }else if (modelClass.isAssignableFrom(EmployeeDetailViewModel.class)) {
      //noinspection unchecked
      return (T) new EmployeeDetailViewModel(dataManager,schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}