package com.sundaegukbap.banchango.container.application;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.container.dto.ContainerInsertRequest;
import com.sundaegukbap.banchango.container.dto.dto.ContainerDto;
import com.sundaegukbap.banchango.container.repository.ContainerRepository;
import com.sundaegukbap.banchango.user.domain.User;
import com.sundaegukbap.banchango.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContainerService {
    private final ContainerRepository containerRepository;
    private final UserRepository userRepository;

    public ContainerService(ContainerRepository containerRepository, UserRepository userRepository) {
        this.containerRepository = containerRepository;
        this.userRepository = userRepository;
    }

    public void createContainer(Long userId, ContainerInsertRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Container container = request.toEntity(user);

        containerRepository.save(container);
    }

    public List<ContainerDto> getAllContainers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        List<Container> containers = containerRepository.findAllByUser(user);

        return ContainerDto.of(containers);
    }
}
