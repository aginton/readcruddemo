import React from 'react';
import reactDom from 'react-dom';
import ListEmployee from './components/ListEmployee';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import EmployeeDetails from './components/EmployeeDetails';


class App extends React.Component {

    
    render() {
        return (
            <Router>
                <h1>Dumn header</h1> 
                
                <div className="container">
                    <Switch>
                        <Route path="/" exact component={ListEmployee}></Route>
                        <Route path="/add-employee" component={EmployeeDetails}></Route>
                        <Route path="/update-employee/:id"  component={EmployeeDetails}></Route>
                        <ListEmployee />
                    </Switch>
                </div>
            </Router>

        );
    }
}

reactDom.render(<App />, document.querySelector("#root"));
