package com.example.demo.model;

import com.example.demo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo repo;

    @GetMapping("/getAllEmployees")
    public List<Employee> getEmployees(){
        return repo.findAll();
    }

    @GetMapping("getEmployeeById")
    public Employee getEmployeeById(Long id){
        return repo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @GetMapping("/getActiveEmployees")
    public List<Employee> getActiveEmployees(){
        List<Employee> res = new ArrayList<>();
        for (Employee employee: repo.findAll()){
            if (employee.getStatus()!=0){
                res.add(employee);
            }
        }
        return res;
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return repo.save(employee);
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable(value="id") Long id, @RequestBody Employee employeeDetails){
        return repo.findById(id).map(employee -> {
            if (employeeDetails.getAddress() != null){
                employee.setAddress(employeeDetails.getAddress());
            }
            if (employeeDetails.getAge() != null){
                employee.setAge(employeeDetails.getAge());
            }
            if (employeeDetails.getName() != null){
                employee.setName((employeeDetails.getName()));
            }
            return repo.save(employee);
        }).orElseGet(()->{
            employeeDetails.setId(id);
            return repo.save(employeeDetails);
        });
    }

    @PutMapping("/updateStatus/{id}")
    public Employee updateStatus(@PathVariable(value="id") Long id, @RequestBody Employee employeeDetails){
        return repo.findById(id)
                .map(employee -> {
                    employee.setStatus(employeeDetails.getStatus());
                    return repo.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void updateStatus(@PathVariable(value="id") Long id){
        Optional<Employee> employee=repo.findById(id);
        Employee foundEmp=employee.get();
        repo.delete(foundEmp);
    }
}
