package com.application.boilerplate.ui.employeelist;

public interface EmployeeListNavigator {

    void handleError(Throwable throwable);

    void openEmployeeListDetailActivity(EmployeeModel employeeModel);

    void showMessage(String message);

    void noEmployeesAvailable();

    void hideKeyboard();
}