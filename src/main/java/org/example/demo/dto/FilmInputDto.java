package org.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link org.example.demo.model.Film}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmInputDto implements Serializable {
    private final Integer id;

    @Size(max = 20, message = "Title must be less than 20 characters")
    private final String title;

    @NotEmpty(message = "Description is required")
    private final String description;
    private final Integer releaseYear;

    @NotNull(message = "Language is required")
    private final Short languageId;
    private final Short originalLanguageId;

    @NotNull(message = "Rental duration is required")
    private final Short rentalDuration;

    @NotNull(message = "Rental rate is required")
    private final BigDecimal rentalRate;
    private final Integer length;

    @NotNull(message = "Replacement cost is required")
    private final BigDecimal replacementCost;
    private final String rating;
    private final String specialFeatures;

    public FilmInputDto(Integer id, String title, String description, Integer releaseYear, Short languageId, Short originalLanguageId, Short rentalDuration, BigDecimal rentalRate, Integer length, BigDecimal replacementCost, String rating, String specialFeatures, Instant lastUpdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Short getLanguageId() {
        return languageId;
    }

    public Short getOriginalLanguageId() {
        return originalLanguageId;
    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmInputDto entity = (FilmInputDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.releaseYear, entity.releaseYear) &&
                Objects.equals(this.languageId, entity.languageId) &&
                Objects.equals(this.originalLanguageId, entity.originalLanguageId) &&
                Objects.equals(this.rentalDuration, entity.rentalDuration) &&
                Objects.equals(this.rentalRate, entity.rentalRate) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.replacementCost, entity.replacementCost) &&
                Objects.equals(this.rating, entity.rating) &&
                Objects.equals(this.specialFeatures, entity.specialFeatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseYear, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "description = " + description + ", " +
                "releaseYear = " + releaseYear + ", " +
                "language = " + languageId + ", " +
                "originalLanguage = " + originalLanguageId + ", " +
                "rentalDuration = " + rentalDuration + ", " +
                "rentalRate = " + rentalRate + ", " +
                "length = " + length + ", " +
                "replacementCost = " + replacementCost + ", " +
                "rating = " + rating + ", " +
                "specialFeatures = " + specialFeatures + ")";
    }
}