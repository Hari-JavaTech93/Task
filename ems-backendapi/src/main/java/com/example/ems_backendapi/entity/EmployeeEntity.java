package com.example.ems_backendapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "employees")
public class EmployeeEntity {
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  	private String name;
	    private String age;
	    private String department;
	    private String position;
	    
	   public EmployeeEntity() {
		// TODO Auto-generated constructor stub
	   }
	    
	    public EmployeeEntity(Long id, String name, String age, String department, String position) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.department = department;
			this.position = position;
		}
		
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		

}
