import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const UpdateEmployee = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [employee, setEmployee] = useState({
    id: id,
    name: "",
    age: "",
    department: "",
    position:"",
  });

  const updateEmployee = (e) => {
    e.preventDefault();
    EmployeeService.updateEmployee(id, employee)
      .then((response) => {
        navigate("/employees");
      })
      .catch((error) => console.log(error));
  };

  const handleChange = (e) => {
    const value = e.target.value;
    setEmployee({ ...employee, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await EmployeeService.getEmployeeById(id);
        setEmployee(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  return (
    <div className="flex max-w-2xl shadow border-b mx-auto">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Update Employee</h1>
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
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
        </div>
        <div className="items-center justify-center h-15 w-full my-4">
          <label className="block text-gray-700 font-normal text-sm">
            Age
          </label>
          <input
            name="age"
            value={employee.age}
            onChange={(e) => handleChange(e)}
            type="text"
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
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
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
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
            className="h-10 w-95 border mt-2 px-2 py-2"
          ></input>
        </div>
        <div className="items-center justify-center h-15 w-full my-4 space-x-4 pt-4">
          <button
            onClick={updateEmployee}
            className=" rounded bg-teal-600 font-semibold text-white py-2 px-4 hover:bg-teal-800"
          >
            Update
          </button>
          <button
            onClick={() => navigate("/employees")}
            className=" rounded bg-teal-500 font-semibold text-white py-2 px-4 hover:bg-teal-800"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpdateEmployee;
