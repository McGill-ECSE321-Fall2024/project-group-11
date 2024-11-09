package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long>{
    //find staff by id
    public Staff findStaffById(Long id);
// lis staff that is the owner if admin ==yes or the list of the employees if admin ==no
        public List<Staff> findStaffByadmin(Boolean admin);

        public Staff findStaffByEmail(String email);

        public Staff findStaffByuserName(String userName);


}