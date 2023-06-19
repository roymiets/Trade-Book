package com.cm.bbuserapi.repository;

import com.cm.bbuserapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findTopByOrderByIdDesc();

    List<User> findAllByNameStartsWith(String name);

    Optional<User> findUserByPhone(String phone);

    Optional<User> findUserByUserName(String userName);

    @Query("SELECT e FROM User e WHERE e.userName LIKE %:term% OR e.name LIKE %:term%")
    List<User> searchUsersByUserNameOrName(@Param("term") String term);
}
