package com.example.demo.model;

import com.example.demo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
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

        LocalTime now = LocalTime.now();

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        System.out.printf("The day of the week is %d\n", day);

        for (Employee employee: repo.findAll()){
            if (employee.isWorkingNow(day, now)){
                res.add(employee);
            }
        }
        return res;
    }

    @GetMapping("/getEmployeesInArea")
    public List<Employee> getEmployeesInArea(@RequestBody Rectangle rectangle){
        List<Employee> res = new ArrayList<>();
        for (Employee employee: repo.findAll()){
            if (employee.getLocation().isWithinRectangle(rectangle)){
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
            employee.setAddress(employeeDetails.getAddress());
            employee.setAge(employeeDetails.getAge());
            employee.setName((employeeDetails.getName()));
            employee.setWorkWeek(employeeDetails.getWorkWeek());
            employee.setLocation(employeeDetails.getLocation());
            return repo.save(employee);
        }).orElseGet(()->{
            employeeDetails.setId(id);
            return repo.save(employeeDetails);
        });
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void updateStatus(@PathVariable(value="id") Long id){
        Optional<Employee> employee=repo.findById(id);
        Employee foundEmp=employee.get();
        repo.delete(foundEmp);
    }
}
