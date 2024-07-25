package com.sundaegukbap.banchango.container.repository;

import com.sundaegukbap.banchango.container.domain.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
}
