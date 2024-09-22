package com.sundaegukbap.banchango.container.dto.dto;

import com.sundaegukbap.banchango.container.domain.Container;

import java.util.List;
import java.util.stream.Collectors;

public record ContainerDto(
        Long containerId,
        String containerName
) {
    public static ContainerDto of(Container container){
        return new ContainerDto(
                container.getId(),
                container.getName());
    }

    public static List<ContainerDto> of(List<Container> containers){
        return containers.stream()
                .map(ContainerDto::of)
                .collect(Collectors.toList());
    }
}
