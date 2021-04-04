import axios from 'axios';


const EMPLOYEE_API_BASE_URL = "http://localhost:9090/";

class EmployeeService{
    getEmployees(){
        return axios.get(EMPLOYEE_API_BASE_URL + "getAllEmployees");
    }

    getEmployeeById(id){
        return axios.get(EMPLOYEE_API_BASE_URL + `getEmployeeById/${id}`);
    }


    

    addEmployee(employee){
        console.log("addEmployee called with the following object:")
        console.log(employee);
        
        const jsonObj = {
            "name": employee.name,
            "age": employee.age,
            "id": employee.id,
            "address": employee.address,
            "workWeek": {
                "sunday": {
                    "startTime": employee.sundayStart === null ? null : employee.sundayStart,
                    "endTime": employee.sundayEnd === null ? null : employee.sundayEnd,
                },
                "monday": {
                    "startTime": employee.mondayStart === null ? null : employee.mondayStart,
                    "endTime": employee.mondayEnd === null ? null : employee.mondayEnd,
                },
                "tuesday": {
                    "startTime": employee.tuesdayStart === null ? null : employee.tuesdayStart,
                    "endTime": employee.tuesdayEnd === null ? null : employee.tuesdayEnd,
                },
                "wednesday": {
                    "startTime": employee.wednesdayStart === null ? null : employee.wednesdayStart,
                    "endTime": employee.wednesdayEnd === null ? null : employee.wednesdayEnd,
                },
                "thursday": {
                    "startTime": employee.thursdayStart === null ? null : employee.thursdayStart,
                    "endTime": employee.thursdayEnd === null ? null : employee.thursdayEnd,
                },
                "friday": {
                    "startTime": employee.fridayStart === null ? null : employee.fridayStart,
                    "endTime": employee.fridayEnd === null ? null : employee.fridayEnd,
                },
                "saturday": {
                    "startTime": employee.saturdayStart === null ? null : employee.saturdayStart,
                    "endTime": employee.saturdayEnd === null ? null : employee.saturdayEnd,
                }
            }
        };

        console.log("Created following jsonObj");
        console.log(jsonObj);

        return axios.post(EMPLOYEE_API_BASE_URL + 'addEmployee',jsonObj)
    }

    updateEmployee(employee, employeeId){
        return axios.put(EMPLOYEE_API_BASE_URL + 'updateEmployee' + employeeId, employee);
    }


}


export default new EmployeeService();