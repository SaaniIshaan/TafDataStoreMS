package com.tekarch.TafDataStoreMS.controllers;

import com.tekarch.TafDataStoreMS.models.Users;
import com.tekarch.TafDataStoreMS.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/datastore")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userServiceImpl.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId)  {
        Users user = userServiceImpl.getUserById(userId);
   //     return new ResponseEntity<>(user,HttpStatus.OK);
          return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();

    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>>getAllusers(){
         return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @PutMapping("/users")
    public ResponseEntity<Users> updatedUser(@RequestBody Users user){
        Users updatedUser = userServiceImpl.updateUserDetails(user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userServiceImpl.deleteUser(userId);
        String message = "User with ID " + userId + "has been successfully deleted";
        return ResponseEntity.ok(message);
    }

}
