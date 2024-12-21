package org.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link org.example.demo.model.Country}
 */
public class CountryDto implements Serializable {
    private final Integer id;
    private final String country;

    public CountryDto(Integer id, String country) {
        this.id = id;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDto entity = (CountryDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.country, entity.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "country = " + country + ")";
    }
}