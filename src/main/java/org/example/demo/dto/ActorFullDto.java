package org.example.demo.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link org.example.demo.model.Actor}
 */
public class ActorFullDto implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Instant lastUpdate;
    private final Set<FilmDto> films;

    public ActorFullDto(Integer id, String firstName, String lastName, Instant lastUpdate,
                        Set<FilmDto> films) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
        this.films = films;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorFullDto entity = (ActorFullDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.lastUpdate, entity.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, lastUpdate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "lastUpdate = " + lastUpdate + ")";
    }

    public Set<FilmDto> getFilms() {
        return films;
    }
}