import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";


const AddEmployee = () => {
  const [employee, setEmployee] = useState({
    id: "",
    name: "",
    age: "",
    department: "",
    position:"",
  });

  
  const [errors, setErrors] = useState({
    id: "",
    name: "",
    age: "",
    department: "",
    position:"",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    let value = e.target.value;
    if(e.target.type=="number"){

      let a=parseInt(value);

      if(a<1){
        value=1;
      }
      else if(a>100){
        value=100;
      }
      
    }

    setErrors({...errors,[e.target.name]:""});

    setEmployee({ ...employee, [e.target.name]: value });
  };


  const validate = (values) => {

    errors;
    if (!values.name) {
      errors.name = "Name is required";
    } 
    if (!values.age) {
      errors.age = "Age is required";
    } 
    if(!values.department){
      errors.department="Department is Required!"
    }
    if(!values.position){
      errors.position="Position is Required!";
    }
    
    return errors;
  }


  const saveEmployee = (e) => {
    e.preventDefault();


   const val= validate(employee);
   setErrors({
       name: val.name,
       age: val.age,
       department: val.department,
       position:val.position,
     });

    if(errors.name=="" && errors.age=="" && errors.department=="" && errors.position==""){
       EmployeeService.saveEmployee(employee)
      .then((response) => {
        console.log(response);
        navigate("/employees");
      })
      .catch((error) => {
        console.log(error);
      });

    }
   
  };

  const reset = (e) => {
    e.preventDefault(); // no refreshing of page
    setEmployee({
      id: "",
      name: "",
      age: "",
      department: "",
      position:"",
    });
  };

  return (
    <div className="flex max-w-2xl shadow border-b mx-auto">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Employee</h1>
        </div>
        <div className="items-center justify-center h-15 w-full my-4">
          <label className="block text-gray-700 font-normal text-sm">
            Name
          </label>
          <input
            name="name"
            value={employee.name}
            onChange={(e) => handleChange(e)}
            type="text"
            required
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
          {errors.name && <p style={{color:"red"}}>{errors.name}</p>}
        </div>
        <div className="items-center justify-center h-15 w-full my-4">
          <label className="block text-gray-700 font-normal text-sm">
            Age
          </label>
          <input
            name="age"
            value={employee.age}
            type="number"
            onChange={(e) => handleChange(e)}
            required
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
          {errors.age && <p style={{color:"red"}}>{errors.age}</p>}
        </div>
        <div className="items-center justify-center h-15 w-full my-4">
          <label className="block text-gray-700 font-normal text-sm">
            Department
          </label>
          <input
            name="department"
            value={employee.department}
            onChange={(e) => handleChange(e)}
            type="text"
            required
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
          {errors.department && <p style={{color:"red"}}>{errors.department}</p>}
        </div>
        <div className="items-center justify-center h-15 w-full my-4">
          <label className="block text-gray-700 font-normal text-sm">
            Position
          </label>
          <input
            name="position"
            value={employee.position}
            onChange={(e) => handleChange(e)}
            type="text"
            required
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
           {errors.position && <p style={{color:"red"}}>{errors.position}</p>}
        </div>
        <div className="items-center justify-center h-15 w-full my-4 space-x-4 pt-4">
          <button
            onClick={saveEmployee}
            className=" rounded bg-teal-600 font-semibold text-white py-2 px-4 hover:bg-teal-800"
          >
            Save
          </button>
          <button
            onClick={reset}
            className=" rounded bg-teal-500 font-semibold text-white py-2 px-4 hover:bg-teal-800"
          >
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddEmployee;
