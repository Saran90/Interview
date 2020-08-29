package com.application.boilerplate.ui.employeelist;

import androidx.databinding.ObservableField;

/**
 * Created by Saran on 10/07/17.
 */

public class EmployeeListItemViewModel {

    public final ObservableField<String> name;
    public final ObservableField<String> profileImage;
    public final ObservableField<String> company;

    private final EmployeeModel mEmployeeModel;
    private EmployeeListItemViewModelListener mListener;

    public EmployeeListItemViewModel(EmployeeModel employeeModel,
                                     EmployeeListItemViewModelListener listener) {
        this.mEmployeeModel = employeeModel;
        name = new ObservableField<>(mEmployeeModel.getName());
        profileImage = new ObservableField<>(mEmployeeModel.getProfileImage());
        company = new ObservableField<>(mEmployeeModel.getCompanyName());
        this.mListener = listener;
    }
    
    public void onItemClick() {
        mListener.onItemClick(mEmployeeModel);
    }

    public EmployeeModel getmEmployeeModel() {
        return mEmployeeModel;
    }

    public interface EmployeeListItemViewModelListener {

        void onItemClick(EmployeeModel employeeModel);
    }
}
