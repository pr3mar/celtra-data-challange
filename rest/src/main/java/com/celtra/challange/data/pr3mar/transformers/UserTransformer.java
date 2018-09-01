package com.celtra.challange.data.pr3mar.transformers;

import com.celtra.challange.data.pr3mar.models.dto.UserDTO;
import com.celtra.challange.data.pr3mar.models.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserTransformer extends GenericTransformer<UserEntity, UserDTO> {

    @Override
    public UserDTO transformToDTO(UserEntity entity) {
        if(entity == null) {
            return null;
        }
        return new UserDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getDateStored()
        );
    }

    @Override
    public List<UserDTO> transformToDTO(List<UserEntity> entities) {
        List<UserDTO> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            if (entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public UserEntity transformToEntity(UserDTO dto) {
        if(dto == null) {
            return null;
        }
        return new UserEntity(
                dto.getId(),
                dto.getEmail()
        );
    }

    @Override
    public List<UserEntity> transformToEntity(List<UserDTO> dtos) {
        List<UserEntity> entities = new ArrayList<>();
        for (UserDTO dto : dtos) {
            if (dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}
