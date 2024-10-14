package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
        public Employee findEmployeeById(int idNum);
}
