package com.application.boilerplate.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Saran M S on 11/2/2019.
 */
@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "employee_id")
    public String employeeId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone")
    public String phone;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "profile_image")
    public String profileImage;

    @ColumnInfo(name = "website")
    public String website;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @ColumnInfo(name = "company_catch_phrase")
    public String companyCatchPhrase;

    @ColumnInfo(name = "company_bs")
    public String companyBs;

    @ColumnInfo(name = "street")
    public String street;

    @ColumnInfo(name = "suite")
    public String suite;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "zipcode")
    public String zipcode;

    @ColumnInfo(name = "latitude")
    public String latitude;

    @ColumnInfo(name = "longitude")
    public String longitude;


}
