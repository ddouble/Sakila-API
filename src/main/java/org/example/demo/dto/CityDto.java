package org.example.demo.dto;

import org.example.demo.model.City;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link City}
 */
public class CityDto implements Serializable {
    private final Integer id;
    private final String city;
    private final CountryDto country;

    public CityDto(Integer id, String city,
                   CountryDto country) {
        this.id = id;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDto entity = (CityDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.city, entity.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "city = " + city + ")";
    }

    public CountryDto getCountry() {
        return country;
    }
}