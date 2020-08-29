package com.application.boilerplate.ui.employeedetail;

import com.application.boilerplate.ui.employeelist.EmployeeModel;

public interface EmployeeDetailNavigator {
    void navigateBack();
    void loadProfileImage(String imageUrl);
}