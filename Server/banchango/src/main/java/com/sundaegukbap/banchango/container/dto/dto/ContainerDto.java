package com.sundaegukbap.banchango.container.dto.dto;

import com.sundaegukbap.banchango.container.domain.Container;

public record ContainerDto(
        Long containerId,
        String containerName
) {
    public static ContainerDto of(Container container){
        return new ContainerDto(
                container.getId(),
                container.getName());
    }
}
