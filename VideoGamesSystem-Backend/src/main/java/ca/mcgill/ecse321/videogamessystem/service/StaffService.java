package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Staff;
import ca.mcgill.ecse321.videogamessystem.repository.StaffRepository;

@Service
public class StaffService {

    private StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Transactional
    public Staff createStaff(String userName, String email, String password, boolean isAdmin) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        if (staffRepository.findStaffByUserName(userName) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (staffRepository.findStaffByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists.");
        }

        Staff staff = new Staff(userName, email, password, isAdmin);
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff getStaffById(Long id) {
        Staff staff = staffRepository.findStaffById(id);
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }
        return staff;
    }

    @Transactional
    public Staff getStaffByUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        return staffRepository.findStaffByUserName(userName);
    }

    @Transactional
    public Staff getStaffByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        return staffRepository.findStaffByEmail(email);
    }

    @Transactional
    public List<Staff> getStaffByAdmin(boolean isAdmin) {
        return staffRepository.findStaffByAdmin(isAdmin);
    }

    @Transactional
    public Staff updateStaffUserName(Long id, String newUserName) {
        Staff staff = staffRepository.findStaffById(id);
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }
        if (newUserName == null || newUserName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (staffRepository.findStaffByUserName(newUserName) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        staff.setUserName(newUserName);  // Assuming `setUserName` exists in the Account superclass
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff updateStaffEmail(Long id, String newEmail) {
        Staff staff = staffRepository.findStaffById(id);
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (staffRepository.findStaffByEmail(newEmail) != null) {
            throw new IllegalArgumentException("Email already exists.");
        }

        staff.setEmail(newEmail);  // Assuming `setEmail` exists in the Account superclass
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff deleteStaff(Long id) {
        Staff staff = staffRepository.findStaffById(id);
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }

        staffRepository.delete(staff);
        return staff;
    }

    @Transactional
    public List<Staff> getAllStaff() {
        return toList(staffRepository.findAll());
    }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}

