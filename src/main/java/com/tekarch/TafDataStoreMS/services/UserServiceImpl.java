package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Bookings;
import com.tekarch.TafDataStoreMS.models.Flights;
import com.tekarch.TafDataStoreMS.models.Users;
import com.tekarch.TafDataStoreMS.repositories.BookingRepository;
import com.tekarch.TafDataStoreMS.repositories.FlightRepository;
import com.tekarch.TafDataStoreMS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepositorysitory){
        this.userRepository = userRepository;
    }

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users updateUserDetails(Users user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
