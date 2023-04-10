package com.example.demo.repository;
import com.example.demo.entity.Employer;

import java.util.List;
public interface IEmployerRepository {


    Employer getByID(Long id);
    void delete(Long id);
    void save(Employer e);
    List<Employer> findAll();
    Employer update(Employer lighting);
}