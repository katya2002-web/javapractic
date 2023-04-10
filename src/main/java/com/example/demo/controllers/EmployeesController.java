package com.example.demo.controllers;

import com.example.demo.entity.Employer;
import com.example.demo.services.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class EmployeesController { //var a= new EmployeeController()
    @Autowired
    private final IEmployeeService employeeService;

    public EmployeesController(IEmployeeService service) {
        employeeService = service;
    }

    @GetMapping("/")
    public String home() {
        employeeService.getList();
        return "home";

    }

    @GetMapping("/change")
    public String change() {
        return "change";

    }

    @GetMapping("/add")
    public String add() {
        return "add";

    }

    @GetMapping("/delete")
    public String delete() {
        return "delete";

    }

    @GetMapping("/inform")
    public String inform() {
        return "inform";

    }

    @Async
    @PostMapping("/add")
    public String addNewEmployee(@ModelAttribute Employer employer) {
        employeeService.saveNewEmployer(employer);
        System.out.println("Add new object");
        return "redirect:/";
    }

    @Async
    @GetMapping("/inform/{id}")
    public String getEmployer(@PathVariable("id") String id, Model model) {
        List<Employer> arr = new ArrayList<>();
        arr.add(employeeService.getEmployerByID(Long.parseLong(id)));
        model.addAttribute("Employer", arr);
        return "inform";}

    @Async
    @PostMapping("/edit/{id}")
    public String editEmployer(@PathVariable("id") String id,
                               @RequestParam(value = "Name", required = false) String Name,
                               @RequestParam(value = "position", required = false) String Position,
                               @RequestParam(value = "Expirience", required = false) String Expirience)
            {
        Employer employer = new Employer(
                Long.parseLong(id),
                Name,
                Position,
                Integer.parseInt(Expirience));




        employeeService.saveNewEmployer(employer);
        return "redirect:/inform";
    }

    @Async
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @Async
    @GetMapping("/inform/get")
    public String get4Buisnes(Model model) {
        model.addAttribute("list", employeeService.getList());
        return "list4Buisnes";
    }
}









