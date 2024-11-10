package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ca.mcgill.ecse321.videogamessystem.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long>{
    public Staff findStaffById(Long id);

    // lis staff that is the owner if admin ==yes or the list of the employees if admin ==no
    public List<Staff> findStaffByadmin(Boolean admin);

}