import React, { useState } from 'react';
import { Button, Form,  Col } from 'react-bootstrap'
import EmployeeService from '../services/EmployeeService';
import { useHistory } from "react-router-dom";



const EmployeeDetails = ({location}) => {

    const [form, setForm] = useState({})
    const [errors, setErrors] = useState({})
    const history = useHistory();


    const setField = (field, value) => {
        // console.log("changed to ");
        // console.log(value);
        setForm({
            ...form,
            [field]: value
        })
        // Check and see if errors exist, and remove them from the error object:
        if (!!errors[field]) setErrors({
            ...errors,
            [field]: null
        })
    }


    const findFormErrors = () => {
        const { name, age } = form
        const newErrors = {}
        // name errors
        if (!name || name === '') newErrors.name = 'cannot be blank!'
        else if (name.length > 30) newErrors.name = 'name is too long!'

        if (!age) newErrors.age = 'Must have an age!'

        return newErrors
    }

    const onCancel = () => {
        history.goBack();
    }

    const handleSubmit = e => {
        e.preventDefault()
        console.log("clicked submit!")

        console.log(form)

        // get our new errors
        const newErrors = findFormErrors()
        console.log(newErrors);
        // Conditional logic:
        if (Object.keys(newErrors).length > 0) {
            // We got errors!
            setErrors(newErrors)
        } else {
            // No errors! Put any logic here for the form submission!
            alert('Employee successfully added!')
            EmployeeService.addEmployee(form).then(res => {
                // this.props.history.push('/employees');
                history.goBack();
            })
        }
    }


    return (
        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Add Employee</h3>
                    <div className="card-body">

                        <Form onSubmit={handleSubmit}>
                            <Form.Row>
                                <Form.Group as={Col} controlId="name">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control type="text" required onChange={e => setField('name', e.target.value)} isInvalid={!!errors.name}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {errors.name}
                                    </Form.Control.Feedback>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="age">
                                    <Form.Label>Age</Form.Label>
                                    <Form.Control type="number" min={0} max={100} required isInvalid={!!errors.age} onChange={e => setField('age', e.target.value)} />
                                    <Form.Control.Feedback type='invalid'> {errors.age} </Form.Control.Feedback>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="address" onChange={e => setField('address', e.target.value)} >
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control type="text" />
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="workWeek:monday:startTime" onChange={e => setField('mondayStart', e.target.value)} >
                                    <Form.Label>Monday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="mondayEnd" onChange={e => setField('mondayEnd', e.target.value)} >
                                    <Form.Label>Monday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="tuesdayStart" onChange={e => setField('tuesdayStart', e.target.value)} >
                                    <Form.Label>Tuesday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="tuesdayEnd" onChange={e => setField('tuesdayEnd', e.target.value)} >
                                    <Form.Label>Tuesday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="wednesdayStart" onChange={e => setField('wednesdayStart', e.target.value)} >
                                    <Form.Label>Wednesday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="wednesdayEnd" onChange={e => setField('wednesdayEnd', e.target.value)} >
                                    <Form.Label>Wednesday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>


                            <Form.Row>
                                <Form.Group as={Col} controlId="thursdayStart" onChange={e => setField('thursdayStart', e.target.value)} >
                                    <Form.Label>Thursday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="thursdayEnd" onChange={e => setField('thursdayEnd', e.target.value)} >
                                    <Form.Label>Thursday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>


                            <Form.Row>
                                <Form.Group as={Col} controlId="fridayStart" onChange={e => setField('fridayStart', e.target.value)} >
                                    <Form.Label>Friday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="fridayEnd" onChange={e => setField('fridayEnd', e.target.value)} >
                                    <Form.Label>Friday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group as={Col} controlId="saturdayStart" onChange={e => setField('saturdayStart', e.target.value)} >
                                    <Form.Label>Saturday Start Time</Form.Label>
                                    <Form.Control type="time" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="saturdayStartEnd" onChange={e => setField('saturdayStartEnd', e.target.value)} >
                                    <Form.Label>Saturday End Time</Form.Label>
                                    <Form.Control type="time"></Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Button type="submit">Submit form</Button>
                            <Button className="btn btn-danger" onClick={onCancel}>Cancel</Button>
                        </Form>

                    </div>
                </div>
            </div>

        </div>
    )
}

export default EmployeeDetails;
