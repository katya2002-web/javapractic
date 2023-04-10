package com.example.demo.services;


import com.example.demo.entity.Employer;

import java.util.List;

public interface IEmployeeService {
    List<Employer> getList();
    void saveNewEmployer(Employer employer);
    void deleteEmployeeById(long id);
    Employer getEmployerByID(long id);
    Employer update(Employer employer);
    void switchLight(long id);
}