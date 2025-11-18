package com.sitco.api.repositories;

import com.sitco.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
