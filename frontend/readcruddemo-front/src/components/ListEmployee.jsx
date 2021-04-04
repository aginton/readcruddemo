import React from 'react';
import EmployeeService from '../services/EmployeeService';

class ListEmployee extends React.Component{
    
    constructor(props){
        super(props);
        this.state = {
            employees: [],
            isLoading: true, 
            selectionFilter: null
        };

        this.deleteEmployee = this.deleteEmployee.bind(this);

    }

    componentDidMount(){
        this.setState({isLoading: true});
        EmployeeService.getEmployees().then((res) => {
            this.setState({employees: res.data});
        })
    }


    addEmployee(id){
        console.log("add employee clicked!");
        this.props.history.push('add-employee')
    };

    updateEmployee(id){
        console.log(`update employee clicked for employee with id ${id}!`);
        EmployeeService.getEmployeeById(id).then((response) => {
            console.log(response);
            this.props.history.push(`update-employee/${id}`)
        })
    };



    deleteEmployee(id){

    };

    

    viewEmployee(id){
        console.log(id);
        this.props.history.push('add-employee')
    };

    render(){
        return(
            <div>
                <h2 className="text-center">Employees List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={() => this.addEmployee()}>
                        Add new Employee
                    </button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Employee Id</th>
                            <th>Employee Name</th>
                            <th>Age</th>
                            <th>Address</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            {this.state.employees.map( employee => (
                                <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.name}</td>
                                <td>{employee.age}</td>
                                <td>{employee.address}</td>
                                <td>
                                    <button className="btn btn-primary" onClick={() => this.updateEmployee(employee.id)}>View/Update</button>
                                    <button className="btn btn-danger" onClick={() => console.log(`Clicked delete for employee ${employee.id}`)}>Delete</button>
                                </td>
                            </tr>
                            )
                            )}
                        </tbody>
                        </table>    
                </div>               
            </div>
        )
    }
}

export default ListEmployee;