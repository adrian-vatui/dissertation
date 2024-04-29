package com.amvatui.monolith.mapper;

import com.amvatui.monolith.dto.ImageDto;
import com.amvatui.monolith.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(Image image);

    Image toEntity(ImageDto imageDto);
}
