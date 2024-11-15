package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.StaffDto.StaffRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.StaffDto.StaffResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Staff;
import ca.mcgill.ecse321.videogamessystem.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * Creates a new staff member.
     *
     * @param staffRequestDto the data transfer object containing the new staff's information.
     * @return a StaffResponseDto containing the created staff's information.
     */
    @PostMapping
    public StaffResponseDto createStaff(@Valid @RequestBody StaffRequestDto staffRequestDto) {
        Staff staff = staffService.createStaff(
                staffRequestDto.getUserName(),
                staffRequestDto.getEmail(),
                staffRequestDto.getPassword(),
                staffRequestDto.getadmin()
        );
        return new StaffResponseDto(staff);
    }

    /**
     * Retrieves a staff member by their ID.
     *
     * @param id the ID of the staff member to retrieve.
     * @return a StaffResponseDto containing the staff member's information.
     */
    @GetMapping("/{id}")
    public StaffResponseDto getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        return new StaffResponseDto(staff);
    }

    /**
     * Retrieves a staff member by their username.
     *
     * @param userName the username of the staff member to retrieve.
     * @return a StaffResponseDto containing the staff member's information.
     */
    @GetMapping("/username/{userName}")
    public StaffResponseDto getStaffByUserName(@PathVariable String userName) {
        Staff staff = staffService.getStaffByUserName(userName);
        return new StaffResponseDto(staff);
    }

    /**
     * Retrieves a staff member by their email.
     *
     * @param email the email of the staff member to retrieve.
     * @return a StaffResponseDto containing the staff member's information.
     */
    @GetMapping("/email/{email}")
    public StaffResponseDto getStaffByEmail(@PathVariable String email) {
        Staff staff = staffService.getStaffByEmail(email);
        return new StaffResponseDto(staff);
    }

    /**
     * Retrieves all staff members with a specific admin status.
     *
     * @param isAdmin the admin status (true for admin, false for non-admin) to filter by.
     * @return a list of StaffResponseDto containing the information of matching staff members.
     */
    @GetMapping("/admin/{isAdmin}")
    public List<StaffResponseDto> getStaffByAdmin(@PathVariable boolean isAdmin) {
        List<Staff> staffList = staffService.getStaffByAdmin(isAdmin);
        return staffList.stream().map(StaffResponseDto::new).collect(Collectors.toList());
    }

    // /**
    //  * Updates the username of a specific staff member.
    //  *
    //  * @param id          the ID of the staff member to update.
    //  * @param newUserName the new username for the staff member.
    //  * @return a StaffResponseDto containing the updated staff member's information.
    //  */
    // @PutMapping("/{id}/username")
    // public StaffResponseDto updateStaffUserName(@PathVariable Long id, @RequestParam String newUserName) {
    //     Staff updatedStaff = staffService.updateStaffUserName(id, newUserName);
    //     return new StaffResponseDto(updatedStaff);
    // }

    // /**
    //  * Updates the email of a specific staff member.
    //  *
    //  * @param id       the ID of the staff member to update.
    //  * @param newEmail the new email for the staff member.
    //  * @return a StaffResponseDto containing the updated staff member's information.
    //  */
    // @PutMapping("/{id}/email")
    // public StaffResponseDto updateStaffEmail(@PathVariable Long id, @RequestParam String newEmail) {
    //     Staff updatedStaff = staffService.updateStaffEmail(id, newEmail);
    //     return new StaffResponseDto(updatedStaff);
    // }

    /**
     * Deletes a staff member by their ID.
     *
     * @param id the ID of the staff member to delete.
     * @return a StaffResponseDto containing the deleted staff member's information.
     */
    @DeleteMapping("/{id}")
    public StaffResponseDto deleteStaff(@PathVariable Long id) {
        Staff deletedStaff = staffService.deleteStaff(id);
        return new StaffResponseDto(deletedStaff);
    }

    /**
     * Retrieves all staff members in the system.
     *
     * @return a list of StaffResponseDto containing information for all staff members.
     */
    @GetMapping
    public List<StaffResponseDto> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return staffList.stream().map(StaffResponseDto::new).collect(Collectors.toList());
    }
}

