package org.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link org.example.demo.model.Film}
 */
public class FilmDto implements Serializable {
    private final Integer id;
    private final String title;
    private final String description;
    private final Integer releaseYear;
    private final LanguageDto language;
    private final LanguageDto originalLanguage;
    private final Short rentalDuration;
    private final BigDecimal rentalRate;
    private final Integer length;
    private final BigDecimal replacementCost;
    private final String rating;
    private final String specialFeatures;

    /**
     * NOTE: timezone of JsonFormat is set to UTC to avoid json serialization issues
     * otherwise, will issue the error: "Could not write JSON: Unsupported field: YearOfEra"
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private final Instant lastUpdate;

    public FilmDto(Integer id, String title, String description, Integer releaseYear, LanguageDto language, LanguageDto originalLanguage, Short rentalDuration, BigDecimal rentalRate, Integer length, BigDecimal replacementCost, String rating, String specialFeatures, Instant lastUpdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.originalLanguage = originalLanguage;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
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

    public LanguageDto getLanguage() {
        return language;
    }

    public LanguageDto getOriginalLanguage() {
        return originalLanguage;
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

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDto entity = (FilmDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.releaseYear, entity.releaseYear) &&
                Objects.equals(this.language, entity.language) &&
                Objects.equals(this.originalLanguage, entity.originalLanguage) &&
                Objects.equals(this.rentalDuration, entity.rentalDuration) &&
                Objects.equals(this.rentalRate, entity.rentalRate) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.replacementCost, entity.replacementCost) &&
                Objects.equals(this.rating, entity.rating) &&
                Objects.equals(this.specialFeatures, entity.specialFeatures) &&
                Objects.equals(this.lastUpdate, entity.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseYear, language, originalLanguage, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "description = " + description + ", " +
                "releaseYear = " + releaseYear + ", " +
                "language = " + language + ", " +
                "originalLanguage = " + originalLanguage + ", " +
                "rentalDuration = " + rentalDuration + ", " +
                "rentalRate = " + rentalRate + ", " +
                "length = " + length + ", " +
                "replacementCost = " + replacementCost + ", " +
                "rating = " + rating + ", " +
                "specialFeatures = " + specialFeatures + ", " +
                "lastUpdate = " + lastUpdate + ")";
    }
}