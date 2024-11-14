package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.videogamessystem.model.Staff;
import ca.mcgill.ecse321.videogamessystem.repository.StaffRepository;
import ca.mcgill.ecse321.videogamessystem.service.StaffService;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    public void setUp() {
        staffRepository.deleteAll();
    }

    @AfterEach
    public void clearDatabase() {
        staffRepository.deleteAll();
    }

    @Test
    public void testCreateStaff() {
        String userName = "staff1";
        String email = "staff1@example.com";
        String password = "password123";
        boolean isAdmin = true;

        Staff staff = staffService.createStaff(userName, email, password, isAdmin);

        assertNotNull(staff);
        assertEquals(userName, staff.getUserName());
        assertEquals(email, staff.getEmail());
        assertEquals(password, staff.getPassword());
        assertEquals(isAdmin, staff.getStaffType());
    }

    @Test
    public void testCreateStaffInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            staffService.createStaff("staff2", " ", "password123", false);
        });
        assertEquals("no empty email", exception.getMessage());
    }

    @Test
    public void testGetStaffById() {
        Staff staff = staffService.createStaff("staff3", "staff3@example.com", "password123", false);
        Long id = staff.getId();

        Staff retrievedStaff = staffService.getStaffById(id);

        assertNotNull(retrievedStaff);
        assertEquals(staff.getUserName(), retrievedStaff.getUserName());
        assertEquals(staff.getEmail(), retrievedStaff.getEmail());
    }

    @Test
    public void testGetNonExistentStaffById() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            staffService.getStaffById(9999L);
        });
        assertEquals("Staff not found.", exception.getMessage());
    }

    @Test
    public void testUpdateStaffUserName() {
        Staff staff = staffService.createStaff("staff4", "staff4@example.com", "password123", false);
        Long id = staff.getId();
        String newUserName = "newStaff4";

        Staff updatedStaff = staffService.updateStaffUserName(id, newUserName);

        assertNotNull(updatedStaff);
        assertEquals(newUserName, updatedStaff.getUserName());
    }

    @Test
    public void testUpdateStaffEmail() {
        Staff staff = staffService.createStaff("staff5", "staff5@example.com", "password123", false);
        Long id = staff.getId();
        String newEmail = "newstaff5@example.com";

        Staff updatedStaff = staffService.updateStaffEmail(id, newEmail);

        assertNotNull(updatedStaff);
        assertEquals(newEmail, updatedStaff.getEmail());
    }

    @Test
    public void testDeleteStaff() {
        Staff staff = staffService.createStaff("staff6", "staff6@example.com", "password123", false);
        Long id = staff.getId();

         Staff deletedStaff = staffService.deleteStaff(id);

         assertNotNull(deletedStaff);
         assertEquals("staff6", deletedStaff.getUserName());
         assertNull(staffRepository.findStaffById(id));
    }

    @Test
    public void testDeleteNonExistentStaff() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            staffService.deleteStaff(9999L);
        });
        assertEquals("Staff not found.", exception.getMessage());
    }

    // New test for creating duplicate employee based on username
     @Test
     public void testCreateDuplicateEmployee() {
         Staff existingEmployee = staffService.createStaff("employee3", "employee3@example.com", "password123", false);
        
         Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             staffService.createStaff("employee3", "anotheremail@example.com", "newpass123", false);
         });

        assertEquals("UserName already exists", exception.getMessage());
    }
}
