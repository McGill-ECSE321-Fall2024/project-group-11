package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.videogamessystem.exception.VideoGamesSystemException;
import ca.mcgill.ecse321.videogamessystem.model.Staff;
import ca.mcgill.ecse321.videogamessystem.repository.StaffRepository;
import ca.mcgill.ecse321.videogamessystem.service.StaffService;

public class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        staffRepository.deleteAll();
    }

    @Test
    public void testCreateStaff_Success() {
        String userName = "staff1";
        String email = "staff1@example.com";
        String password = "password123";
        boolean isAdmin = true;

        Staff mockStaff = new Staff(userName, email, password, isAdmin);
        when(staffRepository.save(any(Staff.class))).thenReturn(mockStaff);

        Staff createdStaff = staffService.createStaff(userName, email, password, isAdmin);

        assertNotNull(createdStaff);
        assertEquals(userName, createdStaff.getUserName());
        assertEquals(email, createdStaff.getEmail());
        assertEquals(password, createdStaff.getPassword());
        assertEquals(isAdmin, createdStaff.getStaffType());
    }

    @Test
    public void testCreateStaff_EmailAlreadyExists() {
        String email = "staff1@example.com";
        when(staffRepository.findStaffByEmail(email)).thenReturn(new Staff());

        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            staffService.createStaff("userName", email, "password123", false);
        });

        assertEquals("Email already exists", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testGetStaffById_StaffExists() {
        Staff mockStaff = new Staff("staff3", "staff3@example.com", "password123", false);
        when(staffRepository.findStaffById(1L)).thenReturn(mockStaff);

        Staff foundStaff = staffService.getStaffById(1L);

        assertNotNull(foundStaff);
        assertEquals("staff3", foundStaff.getUserName());
        assertEquals("staff3@example.com", foundStaff.getEmail());
    }

    @Test
    public void testGetStaffById_StaffNotFound() {
        when(staffRepository.findStaffById(9999L)).thenReturn(null);

        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            staffService.getStaffById(9999L);
        });

        assertEquals("Staff not found.", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    // @Test
    // public void testUpdateStaffUserName_Success() {
    //     Staff mockStaff = new Staff("staff4", "staff4@example.com", "password123", false);
    //     when(staffRepository.findStaffById(1L)).thenReturn(mockStaff);
    //     when(staffRepository.save(any(Staff.class))).thenReturn(mockStaff);

    //     Staff updatedStaff = staffService.updateStaffUserName(1L, "newStaff4");

    //     assertNotNull(updatedStaff);
    //     assertEquals("newStaff4", updatedStaff.getUserName());
    // }

    @Test
    public void testDeleteStaff_Success() {
        Staff mockStaff = new Staff("staff6", "staff6@example.com", "password123", false);
        when(staffRepository.findStaffById(1L)).thenReturn(mockStaff);
        doNothing().when(staffRepository).delete(mockStaff);

        Staff deletedStaff = staffService.deleteStaff(1L);

        assertNotNull(deletedStaff);
        assertEquals("staff6", deletedStaff.getUserName());
        verify(staffRepository, times(1)).delete(mockStaff);
    }

    // @Test
    // public void testSetOwner_NoCurrentOwner() {
    //     Staff mockStaff = mock(Staff.class);
    //     when(staffRepository.findStaffById(1L)).thenReturn(mockStaff);
    //     when(staffRepository.findStaffByAdmin(true)).thenReturn(new ArrayList<>());

    //     when(staffRepository.save(mockStaff)).thenReturn(mockStaff);

    //     Staff updatedOwner = staffService.setOwner(1L);

    //     assertNotNull(updatedOwner);
    //     verify(mockStaff).setStaffType(true);
    //     verify(staffRepository, times(1)).save(mockStaff);
    // }

    @Test
    public void testToList_EmptyIterable() {
        // Arrange
        List<Integer> emptyList = new ArrayList<>();

        // Act
        List<Integer> result = new ArrayList<>();
        for (Integer element : emptyList) {
            result.add(element);
        }

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");
    }

    @Test
    public void testToList_MultipleElements() {
        // Arrange
        List<String> input = Arrays.asList("One", "Two", "Three");

        // Act
        List<String> result = new ArrayList<>();
        for (String element : input) {
            result.add(element);
        }

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(3, result.size(), "Result list should have three elements");
        assertTrue(result.containsAll(input), "Result should contain all elements from the input");
    }
}


