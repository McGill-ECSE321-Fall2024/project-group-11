package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.StaffDto.StaffRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.StaffDto.StaffResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Staff;
import ca.mcgill.ecse321.videogamessystem.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Create a new staff member
    @PostMapping
    public StaffResponseDto createStaff(@RequestBody StaffRequestDto staffRequestDto) {
        Staff staff = staffService.createStaff(
                staffRequestDto.getUserName(),
                staffRequestDto.getEmail(),
                staffRequestDto.getPassword(),
                staffRequestDto.getadmin()
        );
        return new StaffResponseDto(staff);
    }

    // Get staff member by ID
    @GetMapping("/{id}")
    public StaffResponseDto getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        return new StaffResponseDto(staff);
    }

    // Get staff member by username
    @GetMapping("/username/{userName}")
    public StaffResponseDto getStaffByUserName(@PathVariable String userName) {
        Staff staff = staffService.getStaffByUserName(userName);
        return new StaffResponseDto(staff);
    }

    // Get staff member by email
    @GetMapping("/email/{email}")
    public StaffResponseDto getStaffByEmail(@PathVariable String email) {
        Staff staff = staffService.getStaffByEmail(email);
        return new StaffResponseDto(staff);
    }

    // Get all staff members with specific admin status
    @GetMapping("/admin/{isAdmin}")
    public List<StaffResponseDto> getStaffByAdmin(@PathVariable boolean isAdmin) {
        List<Staff> staffList = staffService.getStaffByAdmin(isAdmin);
        return staffList.stream().map(StaffResponseDto::new).collect(Collectors.toList());
    }

    // Update staff username
    @PutMapping("/{id}/username")
    public StaffResponseDto updateStaffUserName(@PathVariable Long id, @RequestParam String newUserName) {
        Staff updatedStaff = staffService.updateStaffUserName(id, newUserName);
        return new StaffResponseDto(updatedStaff);
    }

    // Update staff email
    @PutMapping("/{id}/email")
    public StaffResponseDto updateStaffEmail(@PathVariable Long id, @RequestParam String newEmail) {
        Staff updatedStaff = staffService.updateStaffEmail(id, newEmail);
        return new StaffResponseDto(updatedStaff);
    }

    // Delete staff member by ID
    @DeleteMapping("/{id}")
    public StaffResponseDto deleteStaff(@PathVariable Long id) {
        Staff deletedStaff = staffService.deleteStaff(id);
        return new StaffResponseDto(deletedStaff);
    }

    // Get all staff members
    @GetMapping
    public List<StaffResponseDto> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return staffList.stream().map(StaffResponseDto::new).collect(Collectors.toList());
    }
}
