package org.example.demo.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link org.example.demo.model.Inventory}
 */
public class InventoryDto implements Serializable {
    private final Integer id;
    private final Instant lastUpdate;
    private final StoreDto store;

    public InventoryDto(Integer id, Instant lastUpdate, StoreDto store) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.store = store;
    }

    public Integer getId() {
        return id;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public StoreDto getStore() {
        return store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryDto entity = (InventoryDto) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lastUpdate = " + lastUpdate + ")";
    }

}