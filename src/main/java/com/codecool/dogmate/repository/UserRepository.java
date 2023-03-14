package com.codecool.dogmate.repository;

import com.codecool.dogmate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (name, email, password) VALUES (:#{#user.name}, :#{#user.email}, :#{#user.password})", nativeQuery = true)
    void registerUser(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (profile_picture_location) VALUES (:#{#profilePictureLocation})", nativeQuery = true)
    void editedProfilePictureLocation(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (avatar_small_location) VALUES (:#{#avatarSmallLocation})", nativeQuery = true)
    void editedAvatarSmallLocation(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (description) VALUES (:#{#description})", nativeQuery = true)
    void editedDescription(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (is_locked) VALUES (:#{#isLocked})", nativeQuery = true)
    void editedIsLocked(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (is_banned) VALUES (:#{#isBanned})", nativeQuery = true)
    void editedIsBanned(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (ban_expiration) VALUES (:#{#banExpiration})", nativeQuery = true)
    void editedBanExpiration(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (created_at) VALUES (:#{#createdAt})", nativeQuery = true)
    void editedCreatedAt(User user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (is_active) VALUES (:#{isActive})", nativeQuery = true)
    void editedIsActive(User user);
    List<User> findAllByIsActiveTrue();

    Optional<User> findUserByEmail(String email);

}
