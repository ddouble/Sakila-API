package org.example.demo.mapper;

import org.example.demo.dto.StoreDto;
import org.example.demo.model.Store;
import org.mapstruct.*;

@Mapper()
public interface StoreMapper {
    Store toEntity(StoreDto storeDto);

    @Mapping(target = "storeName", source = "address.address")
    @Mapping(target = "cityName", source = "address.city.city")
    StoreDto toDto(Store store);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Store partialUpdate(StoreDto storeDto, @MappingTarget Store store);
}