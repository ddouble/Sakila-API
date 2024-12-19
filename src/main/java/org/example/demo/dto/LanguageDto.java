package org.example.demo.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link org.example.demo.model.Language}
 */
public class LanguageDto implements Serializable {
    private final Short id;
    private final String name;
//    private final Instant lastUpdate;

    public LanguageDto(Short id, String name, Instant lastUpdate) {
        this.id = id;
        this.name = name;
//        this.lastUpdate = lastUpdate;
    }

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public Instant getLastUpdate() {
//        return lastUpdate;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageDto entity = (LanguageDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name); // &&
//                Objects.equals(this.lastUpdate, entity.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", ";
//                "lastUpdate = " + lastUpdate + ")";
    }
}