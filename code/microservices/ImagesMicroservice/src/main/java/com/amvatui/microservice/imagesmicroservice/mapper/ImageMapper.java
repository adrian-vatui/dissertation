package com.amvatui.microservice.imagesmicroservice.mapper;

import com.amvatui.microservice.imagesmicroservice.dto.ImageDto;
import com.amvatui.microservice.imagesmicroservice.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(Image image);

    Image toEntity(ImageDto imageDto);
}
