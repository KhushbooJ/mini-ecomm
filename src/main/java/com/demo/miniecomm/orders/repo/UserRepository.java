package com.demo.miniecomm.orders.repo;

import com.demo.miniecomm.orders.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<Long> saveUserDetails(User user);

    Optional<User> findByEmail(String email);


}
