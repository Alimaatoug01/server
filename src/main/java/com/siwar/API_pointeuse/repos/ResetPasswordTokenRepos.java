package com.siwar.API_pointeuse.repos;

import com.siwar.API_pointeuse.entity.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenRepos extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);
}