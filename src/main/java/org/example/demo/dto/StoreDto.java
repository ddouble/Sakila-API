package org.example.demo.dto;

import org.example.demo.model.Store;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Store}
 */
public class StoreDto implements Serializable {
    private final Short id;
    private final Instant lastUpdate;
    private final AddressDto address;

    public StoreDto(Short id, Instant lastUpdate,
                    AddressDto address) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.address = address;
    }

    public Short getId() {
        return id;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDto entity = (StoreDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.lastUpdate, entity.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastUpdate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lastUpdate = " + lastUpdate + ")";
    }

    public AddressDto getAddress() {
        return address;
    }
}