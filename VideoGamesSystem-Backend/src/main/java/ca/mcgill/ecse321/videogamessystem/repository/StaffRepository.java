package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, Integer>{
    public Staff findStaffById(int idNum);
}
