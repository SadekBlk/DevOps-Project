package com.belkhiria.mystore.controllers;


import com.belkhiria.mystore.dtos.UserResponse;
import com.belkhiria.mystore.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity.ok(adminService.findAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{user-id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(adminService.findById(userId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete-user/{user-id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user-id") Integer userId) {
        adminService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

}

