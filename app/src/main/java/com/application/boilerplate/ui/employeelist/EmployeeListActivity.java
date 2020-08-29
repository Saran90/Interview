package com.application.boilerplate.ui.employeelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.application.boilerplate.R;
import com.application.boilerplate.BR;
import com.application.boilerplate.ViewModelProviderFactory;
import com.application.boilerplate.databinding.ActivityEmployeeListBinding;
import com.application.boilerplate.ui.base.BaseActivity;
import com.application.boilerplate.ui.employeedetail.EmployeeDetailActivity;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.application.boilerplate.ui.employeedetail.EmployeeDetailActivity.EXTRA_SELECTED_EMPLOYEE;

/**
 * Created by SARAN M S on 03/03/20.
 */

public class EmployeeListActivity extends BaseActivity<ActivityEmployeeListBinding, EmployeeListViewModel> implements EmployeeListNavigator, EmployeeListAdapter.EmployeeListAdapterListener {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    EmployeeListAdapter mEmployeeListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private EmployeeListViewModel mEmployeeListViewModel;

    private ActivityEmployeeListBinding mActivityEmployeeListBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, EmployeeListActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_employee_list;
    }

    @Override
    public EmployeeListViewModel getViewModel() {
        mEmployeeListViewModel = ViewModelProviders.of(this, factory).get(EmployeeListViewModel.class);
        return mEmployeeListViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.d(EmployeeListActivity.class.getName(), throwable.getMessage());
    }

    @Override
    public void openEmployeeListDetailActivity(EmployeeModel employeeModel) {

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void noEmployeesAvailable() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEmployeeListBinding = getViewDataBinding();
        mEmployeeListViewModel.setNavigator(this);
        setUpRecyclerView();
        setUpTextWatcher();
        mEmployeeListViewModel.getSearchedEmployeeListMutableLiveData().observe(this, employeeModels -> {
            mEmployeeListAdapter.addItems(employeeModels);
        });
        mEmployeeListAdapter.setListener(this::onEmployeeClicked);
    }

    private void setUpTextWatcher() {
        mActivityEmployeeListBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEmployeeListViewModel.onSearchTextChanged(s.toString());
            }
        });
    }

    private void setUpRecyclerView() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityEmployeeListBinding.patientListRecyclerView.setLayoutManager(mLayoutManager);
        mActivityEmployeeListBinding.patientListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityEmployeeListBinding.patientListRecyclerView.setAdapter(mEmployeeListAdapter);
    }

    @Override
    public void onEmployeeClicked(EmployeeModel employeeModel) {
        Intent intent = EmployeeDetailActivity.newIntent(this);
        intent.putExtra(EXTRA_SELECTED_EMPLOYEE,employeeModel);
        startActivity(intent);
    }
}
