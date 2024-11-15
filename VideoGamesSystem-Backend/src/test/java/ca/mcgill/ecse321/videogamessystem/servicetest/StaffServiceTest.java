package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Method;

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
        MockitoAnnotations.openMocks(this);
        staffRepository.deleteAll();
    }

    @AfterEach
    public void clearDatabase() {
        staffRepository.deleteAll();
    }

    private <T> List<T> invokeToList(Iterable<T> iterable) throws Exception {
        Method toListMethod = StaffService.class.getDeclaredMethod("toList", Iterable.class);
        toListMethod.setAccessible(true); // Make the private method accessible
        return (List<T>) toListMethod.invoke(staffService, iterable);
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
    public void testDeleteNonExistentStaff() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            staffService.deleteStaff(9999L);
        });
        assertEquals("Staff not found.", exception.getMessage());
    }

    // Uncomment and complete this test if the functionality is implemented in StaffService
    // @Test
    // public void testCreateDuplicateEmployee() {
    //     Staff existingEmployee = staffService.createStaff("employee3", "employee3@example.com", "password123", false);
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         staffService.createStaff("employee3", "anotheremail@example.com", "newpass123", false);
    //     });
    //     assertEquals("Username already exists.", exception.getMessage());
    // }


    // @Test
    // public void testSetOwner_StaffNotFound() {
    //     // Arrange
    //     Long nonExistentId = 999L;
    //     when(staffRepository.findStaffById(nonExistentId)).thenReturn(null);

    //     // Act & Assert
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         staffService.setOwner(nonExistentId);
    //     });
    //     assertEquals("Staff not found.", exception.getMessage());
    // }

    // @Test
    // public void testSetOwner_NoCurrentOwner() {
    //     // Arrange
    //     Long staffId = 1L;
    //     Staff mockStaff = mock(Staff.class); // Mocking the Staff object

    //     // Mock the behavior of staffRepository methods
    //     when(staffRepository.findStaffById(staffId)).thenReturn(mockStaff);
    //     when(staffRepository.findStaffByAdmin(true)).thenReturn(List.of());  // No current owner
    //     when(staffRepository.save(mockStaff)).thenReturn(mockStaff);

    //     // Act
    //     Staff updatedStaff = staffService.setOwner(staffId);

    //     // Assert
    //     assertNotNull(updatedStaff);
    //     verify(mockStaff).setStaffType(true); // Verify the new owner status is set
    //     verify(staffRepository, times(1)).save(mockStaff); // Ensure it is saved
    // }

    // @Test
    // public void testSetOwner_WithCurrentOwner() {
    //     // Arrange
    //     Long newOwnerId = 2L;
    //     Staff currentOwner = mock(Staff.class);
    //     when(currentOwner.getStaffType()).thenReturn(true); // Current owner

    //     Staff newOwner = mock(Staff.class);
    //     when(newOwner.getStaffType()).thenReturn(false); // Not an owner initially

    //     when(staffRepository.findStaffById(newOwnerId)).thenReturn(newOwner);
    //     when(staffRepository.findStaffByAdmin(true)).thenReturn(List.of(currentOwner));
    //     when(staffRepository.save(any(Staff.class))).thenAnswer(invocation -> invocation.getArgument(0));

    //     // Act
    //     Staff updatedStaff = staffService.setOwner(newOwnerId);

    //     // Assert
    //     assertNotNull(updatedStaff);
    //     verify(newOwner).setStaffType(true);    // New owner is promoted
    //     verify(currentOwner).setStaffType(false); // Current owner is demoted
    //     verify(staffRepository, times(1)).save(currentOwner);
    //     verify(staffRepository, times(1)).save(newOwner);
    // }

    @Test
    public void testToList_WithEmptyIterable() throws Exception {
        // Arrange
        List<Integer> emptyList = new ArrayList<>();

        // Act
        List<Integer> result = invokeToList(emptyList);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");
    }

    @Test
    public void testToList_WithSingleElement() throws Exception {
        // Arrange
        List<String> singleElementList = Arrays.asList("SingleElement");

        // Act
        List<String> result = invokeToList(singleElementList);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "Result list should have one element");
        assertEquals("SingleElement", result.get(0), "Element should match the input");
    }

    @Test
    public void testToList_WithMultipleElements() throws Exception {
        // Arrange
        List<String> multipleElementsList = Arrays.asList("Element1", "Element2", "Element3");

        // Act
        List<String> result = invokeToList(multipleElementsList);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(3, result.size(), "Result list should have three elements");
        assertEquals("Element1", result.get(0), "First element should match");
        assertEquals("Element2", result.get(1), "Second element should match");
        assertEquals("Element3", result.get(2), "Third element should match");
    }

    // @Test
    // public void testToList_WithNullIterable() throws Exception {
    //     // Arrange
    //     Iterable<String> nullIterable = null;

    //     // Act & Assert
    //     Exception exception = assertThrows(NullPointerException.class, () -> {
    //         invokeToList(nullIterable);
    //     });
    //     assertEquals("Cannot invoke \"java.lang.Iterable.iterator()\" because \"iterable\" is null", exception.getMessage());
    // }


    // @Test
    // public void testGetAllStaff_SingleStaffMember() {
    //     // Arrange
    //     Staff staff = new Staff();
    //     staff.setUserName("staff1");
    //     staff.setEmail("staff1@example.com");

    //     when(staffRepository.findAll()).thenReturn(List.of(staff));

    //     // Act
    //     List<Staff> staffList = staffService.getAllStaff();

    //     // Assert
    //     assertNotNull(staffList, "Result should not be null");
    //     assertEquals(1, staffList.size(), "Result list should contain one staff member");
    //     assertEquals(staff, staffList.get(0), "The staff member should match the one in the repository");
    // }

    // @Test
    // public void testGetAllStaff_MultipleStaffMembers() {
    //     // Arrange
    //     Staff staff1 = new Staff();
    //     staff1.setUserName("staff1");
    //     staff1.setEmail("staff1@example.com");

    //     Staff staff2 = new Staff();
    //     staff2.setUserName("staff2");
    //     staff2.setEmail("staff2@example.com");

    //     when(staffRepository.findAll()).thenReturn(List.of(staff1, staff2));

    //     // Act
    //     List<Staff> staffList = staffService.getAllStaff();

    //     // Assert
    //     assertNotNull(staffList, "Result should not be null");
    //     assertEquals(2, staffList.size(), "Result list should contain two staff members");
    //     assertTrue(staffList.contains(staff1), "Result list should contain staff1");
    //     assertTrue(staffList.contains(staff2), "Result list should contain staff2");
    // }

    // @Test
    // public void testGetAllStaff_DuplicateStaffMembers() {
    //     // Arrange
    //     Staff staff1 = new Staff();
    //     staff1.setUserName("staff1");
    //     staff1.setEmail("staff1@example.com");

    //     when(staffRepository.findAll()).thenReturn(List.of(staff1, staff1));

    //     // Act
    //     List<Staff> staffList = staffService.getAllStaff();

    //     // Assert
    //     assertNotNull(staffList, "Result should not be null");
    //     assertEquals(2, staffList.size(), "Result list should contain two entries even if duplicates exist");
    //     assertEquals(staff1, staffList.get(0), "First staff in the list should match the duplicate staff object");
    //     assertEquals(staff1, staffList.get(1), "Second staff in the list should match the duplicate staff object");
    // }
    
    // @Test
    // public void testGetAllStaff_NullReturnFromRepository() {
    //     // Arrange
    //     when(staffRepository.findAll()).thenReturn(null);

    //     // Act
    //     List<Staff> staffList = staffService.getAllStaff();

    //     // Assert
    //     assertNull(staffList, "Result should be null if repository returns null");
    // }

    // @Test
    // public void testGetAllStaff_MixedStaffTypes() {
    //     // Arrange
    //     Staff activeStaff = new Staff();
    //     activeStaff.setUserName("activeStaff");
    //     activeStaff.setEmail("active@example.com");
    //     activeStaff.setStaffType(true);  // assuming 'true' means active/admin

    //     Staff inactiveStaff = new Staff();
    //     inactiveStaff.setUserName("inactiveStaff");
    //     inactiveStaff.setEmail("inactive@example.com");
    //     inactiveStaff.setStaffType(false);  // assuming 'false' means inactive/non-admin

    //     when(staffRepository.findAll()).thenReturn(List.of(activeStaff, inactiveStaff));

    //     // Act
    //     List<Staff> staffList = staffService.getAllStaff();

    //     // Assert
    //     assertNotNull(staffList, "Result should not be null");
    //     assertEquals(2, staffList.size(), "Result list should contain both active and inactive staff");
    //     assertTrue(staffList.contains(activeStaff), "Result should contain the active staff member");
    //     assertTrue(staffList.contains(inactiveStaff), "Result should contain the inactive staff member");
    // }
}
