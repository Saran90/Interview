package com.application.boilerplate.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.application.boilerplate.data.model.db.Employee;

import java.util.List;

/**
 * Created by Saran M S on 11/2/2019.
 */
@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Employee> employeeList);

    @Query("SELECT * FROM Employee")
    List<Employee> loadAll();

    @Query("DELETE FROM Employee")
    void deleteAll();
}
