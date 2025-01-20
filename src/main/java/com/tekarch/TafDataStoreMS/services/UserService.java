package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Bookings;
import com.tekarch.TafDataStoreMS.models.Flights;
import com.tekarch.TafDataStoreMS.models.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users addUser(Users user);
    Users getUserById(Long userId);
    List<Users> getAllUsers();
    Users updateUserDetails(Users user);
    void deleteUser(Long userId);

}
