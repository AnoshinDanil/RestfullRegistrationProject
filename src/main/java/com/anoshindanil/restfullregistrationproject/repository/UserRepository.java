package com.anoshindanil.restfullregistrationproject.repository;

import com.anoshindanil.restfullregistrationproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean isUserNameExists(String username);
    Boolean isPasswordExists(String username);
}
