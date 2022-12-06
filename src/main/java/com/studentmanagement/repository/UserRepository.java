package com.studentmanagement.repository;

import com.studentmanagement.entity.Role;
import com.studentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Role role);

    @Query("update User u set u.username= ?1, u.email = ?2, u.password = ?3 where u.userid = ?4")
    @Modifying
    void setUserInfoById(String username, String email, String password, long userid);
}
