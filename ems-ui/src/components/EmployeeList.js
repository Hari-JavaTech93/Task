import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import Employee from "./Employee";
import Swal from "sweetalert2";

const EmployeeList = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await EmployeeService.getEmployees();
        setEmployees(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  const deleteEmployee = (e, id) => {
    e.preventDefault();

    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {

      if (result.isConfirmed) {
        EmployeeService.deleteEmployee(id).then((res) => {
          if (employees) {
            setEmployees((prevElement) => {
              return prevElement.filter((employee) => employee.id !== id);
            });
          }
        });

        Swal.fire({
          title: "Deleted!",
          text: "Your file has been deleted.",
          icon: "success",
          confirmButtonColor: "#3085d6"
        });

      }
    });


    


    
  };

  return (
    <div className="container mx-auto my-8">
      <div className="h-12 my-5">
        <button
          className="rounded bg-teal-700 text-white px-2 py-2 font-semibold"
          onClick={() => navigate("/employees/add")}
        >
          Add Employee
        </button>
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-teal-50">
            <tr>
            <th className="text-left font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Id
              </th>
              <th className="text-left font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Name
              </th>
              <th className="text-left font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Age
              </th>
              <th className="text-left font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Department
              </th>
              <th className="text-left font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Position
              </th>
              <th className="text-right font-medium text-teal-800 uppercase tracking-wider px-5 py-3">
                Actions
              </th>
            </tr>
          </thead>
          {!loading &&  employees && (
            <tbody className="bg-gray-50">
              {employees.map((employee) => (
                <Employee
                  employee={employee}
                  deleteEmployee={deleteEmployee}
                  key={employee.id}
                />
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default EmployeeList;
