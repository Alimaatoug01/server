package com.siwar.API_pointeuse.repos;

import com.siwar.API_pointeuse.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.siwar.API_pointeuse.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRole(Role role);
    int countByRole(Role role);

    Optional<User> findByUsername(String username);

}
