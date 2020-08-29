package com.application.boilerplate.ui.employeedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.application.boilerplate.BR;
import com.application.boilerplate.R;
import com.application.boilerplate.ViewModelProviderFactory;
import com.application.boilerplate.databinding.ActivityEmployeeDetailBinding;
import com.application.boilerplate.databinding.ActivityEmployeeDetailBinding;
import com.application.boilerplate.ui.base.BaseActivity;
import com.application.boilerplate.ui.employeelist.EmployeeListAdapter;
import com.application.boilerplate.ui.employeelist.EmployeeModel;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class EmployeeDetailActivity extends BaseActivity<ActivityEmployeeDetailBinding, EmployeeDetailViewModel> implements EmployeeDetailNavigator {

    public static final String EXTRA_SELECTED_EMPLOYEE = "EXTRA_SELECTED_EMPLOYEE";

    @Inject
    ViewModelProviderFactory factory;

    private EmployeeDetailViewModel mEmployeeDetailViewModel;

    private ActivityEmployeeDetailBinding mActivityEmployeeDetailBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, EmployeeDetailActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_employee_detail;
    }

    @Override
    public EmployeeDetailViewModel getViewModel() {
        mEmployeeDetailViewModel = ViewModelProviders.of(this, factory).get(EmployeeDetailViewModel.class);
        return mEmployeeDetailViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEmployeeDetailBinding = getViewDataBinding();
        mEmployeeDetailViewModel.setNavigator(this);
        EmployeeModel employeeModel = (EmployeeModel) getIntent().getSerializableExtra(EXTRA_SELECTED_EMPLOYEE);
        mEmployeeDetailViewModel.init(employeeModel);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void loadProfileImage(String imageUrl) {
        Glide.with(this).load(imageUrl).placeholder(R.drawable.ic_image_placeholder).into(mActivityEmployeeDetailBinding.profileImageView);
    }
}
