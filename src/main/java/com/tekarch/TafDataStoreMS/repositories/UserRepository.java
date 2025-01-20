package com.tekarch.TafDataStoreMS.repositories;

import com.tekarch.TafDataStoreMS.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
