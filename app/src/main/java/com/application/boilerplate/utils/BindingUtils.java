package com.application.boilerplate.utils;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.recyclerview.widget.RecyclerView;

import com.application.boilerplate.ui.employeelist.EmployeeListAdapter;
import com.application.boilerplate.ui.employeelist.EmployeeModel;

import java.util.List;

/**
 * Created by Saran on 11/07/17.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addEmployeeList(RecyclerView recyclerView, List<EmployeeModel> employeeModels) {
        EmployeeListAdapter adapter = (EmployeeListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(employeeModels);
        }
    }

}
