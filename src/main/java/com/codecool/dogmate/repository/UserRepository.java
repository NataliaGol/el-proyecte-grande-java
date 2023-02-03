package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (user_name, email, password) VALUES (:#{#user.name}, :#{#user.email}, :#{#user.password})", nativeQuery = true)
    void registerUser(User user);

    List<User> findAllByIsActiveTrue();
}
