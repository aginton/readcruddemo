package com.example.demo.model;

import com.example.demo.exception.EmployeeIdArgException;
import com.example.demo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo repo;

    @GetMapping("/getAllEmployees")
    public List<Employee> getEmployees(){
        return repo.findAll();
    }

    @GetMapping("getEmployeeById/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long id){
        return repo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }


    @GetMapping("/getActiveEmployees")
    public ResponseEntity<Object> getActiveEmployees(){
        List<Employee> res = new ArrayList<>();
        LocalTime now = LocalTime.now();
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        try{
            for (Employee employee: repo.findAll()){
                if (employee.isWorkingNow(day, now)){
                    res.add(employee);
                }
            }
            return new ResponseEntity<Object>(res.toString(), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getEmployeesInArea")
    public List<Employee> getEmployeesInArea(@RequestBody Circle circle){
        List<Employee> res = new ArrayList<>();
        for (Employee employee: repo.findAll()){
            if (employee.getLocation().isWithinCircle(circle)){
                res.add(employee);
            }
        }
        return res;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        if (employee.getId() != null){
            return new ResponseEntity<>(new EmployeeIdArgException().getMessage(), HttpStatus.BAD_REQUEST);
        }
        repo.save(employee);
        return new ResponseEntity<>("Successfully created following employee: \n" + employee, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable(value="id") Long id, @RequestBody Employee employeeDetails){
        if (employeeDetails.getId() != null){
            return new ResponseEntity<>(new EmployeeIdArgException().getMessage(), HttpStatus.BAD_REQUEST);
        }
        Employee employee = repo.findById(id).get();
        if (employee != null){
            employeeDetails.setId(id);
            employee = employeeDetails;
            repo.save(employee);
            return new ResponseEntity<>("Successfully updated employee: \n" + employee, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new EmployeeNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable(value="id") Long id){
        try{
            Optional<Employee> employee=repo.findById(id);
            Employee foundEmp=employee.get();
            repo.delete(foundEmp);
            return new ResponseEntity<>("Employee id " + id + " successfully deleted.", HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            throw new EmployeeNotFoundException(id);
        }

    }
}
