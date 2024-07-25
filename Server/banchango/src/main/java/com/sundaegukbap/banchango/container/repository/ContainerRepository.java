package com.sundaegukbap.banchango.container.repository;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    List<Container> findAllByUser(User user);
}
