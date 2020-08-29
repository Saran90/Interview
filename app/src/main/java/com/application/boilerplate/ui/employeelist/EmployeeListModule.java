package com.application.boilerplate.ui.employeelist;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saran M S on 8/17/2019.
 */
@Module
public class EmployeeListModule {

    @Provides
    EmployeeListAdapter provOrderTakerPatientListAdapter() {
        return new EmployeeListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(EmployeeListActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
