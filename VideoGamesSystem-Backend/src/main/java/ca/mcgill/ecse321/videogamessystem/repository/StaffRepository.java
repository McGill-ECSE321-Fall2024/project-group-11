package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Staff;

import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Long>{
    public Staff findStaffById(Long id);

    Staff findStaffByUserName(String userName);

    Staff findStaffByEmail(String email);

    List<Staff> findStaffByAdmin(boolean admin);
}