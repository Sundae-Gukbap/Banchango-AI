package com.sundaegukbap.banchango.user.repository;

import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
