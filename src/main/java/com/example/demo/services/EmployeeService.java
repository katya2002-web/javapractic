package com.example.demo.services;
import com.example.demo.entity.Employer;
import com.example.demo.repository.IEmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployerRepository jsonRepo;

    public EmployeeService(IEmployerRepository repository) {
        this.jsonRepo = repository;
    }

    public List<Employer> getList() {
        return jsonRepo.findAll();
    }
    @Async
    public void saveNewEmployer(Employer employer) {
        jsonRepo.save(employer);
        System.out.println("Create new empty object");
    }
    @Async
    public void deleteEmployeeById(long id) {
        jsonRepo.delete(id);
    }


    @Async

    public Employer getEmployerByID(long id) {
        return jsonRepo.getByID(id);
    }

    //middleware
    @Async
    public Employer update(Employer employer) {
        jsonRepo.update(employer);
        return jsonRepo.getByID(employer.getId());
    }



    @Async
    public void switchLight(long id) {
         Employer employer = jsonRepo.getByID(id);
        employer.setExpirience(employer.getExpirience());
        jsonRepo.update(employer);
    }
}