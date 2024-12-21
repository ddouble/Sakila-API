package org.example.demo.mapper;

import org.example.demo.dto.InventoryDto;
import org.example.demo.model.Inventory;
import org.mapstruct.*;

@Mapper(uses = {StoreMapper.class})
public interface InventoryMapper {
    Inventory toEntity(InventoryDto inventoryDto);

    InventoryDto toDto(Inventory inventory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Inventory partialUpdate(InventoryDto inventoryDto, @MappingTarget Inventory inventory);
}