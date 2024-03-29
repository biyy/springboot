package com.spring.cache.controller;

import com.spring.cache.bean.Employee;
import com.spring.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        System.out.println("EmployeeController: "+Thread.currentThread().getName());
        Employee employee = employeeService.getById(id);
        return employee;
    }

    @PostMapping("/emp")
    public Employee update(Employee employee){
        return employeeService.updateEmp(employee);
    }
//
//    @GetMapping("/delemp")
//    public String deleteEmp(Integer id){
//        employeeService.deleteEmp(id);
//        return "success";
//    }
//
//    @GetMapping("/emp/lastname/{lastName}")
//    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
//       return employeeService.getEmpByLastName(lastName);
//    }

}
