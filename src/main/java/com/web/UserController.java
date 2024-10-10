package com.web;

import com.dto.get.UserGetDTO;
import com.dto.post.UserPostDTO;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API to manage users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Get all users.
     *
     * @return A list of UserGetDTO representing all users.
     */
    @Operation(summary = "Get all users", description = "Retrieve a list of all users stored in the database.")
    @GetMapping("")
    public Iterable<UserGetDTO> all() {
        return userService.getAll();
    }

    /**
     * Get a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserGetDTO of the requested user.
     */
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user by providing their ID.")
    @GetMapping("/{id}")
    public UserGetDTO one(@PathVariable("id") final Long id) {
        return userService.get(id);
    }

    /**
     * Delete a specific user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    @Operation(summary = "Delete a user", description = "Delete a user by their ID.")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userService.delete(id);
    }

    /**
     * Add a new user. This action requires admin privileges.
     *
     * @param newUser The data of the new user to create.
     * @return The created user's details in UserGetDTO format.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new user", description = "Create a new user. Admin privileges are required for this action.")
    @PostMapping(value = "", consumes = "application/json")
    public UserGetDTO addUser(@RequestBody UserPostDTO newUser) {
        return userService.post(newUser);
    }

    /**
     * Update an existing user. This action requires admin privileges.
     *
     * @param newUserDto The updated data for the user.
     * @param id         The ID of the user to update.
     * @return The updated user's details in UserGetDTO format.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a user", description = "Update the details of an existing user. Admin privileges are required for this action.")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public UserGetDTO updateUser(@RequestBody UserPostDTO newUserDto, @PathVariable Long id) {
        return userService.put(id, newUserDto);
    }
}