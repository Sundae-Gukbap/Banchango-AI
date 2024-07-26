package com.sundaegukbap.banchango.container.dto;

import com.sundaegukbap.banchango.container.domain.Container;
import com.sundaegukbap.banchango.user.domain.User;

public record ContainerInsertRequest(
        String containerName
) {
    public Container toEntity(User user){
        return Container.builder()
                .name(containerName)
                .user(user)
                .build();
    }
}
