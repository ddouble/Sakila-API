package org.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "city", schema = "sakila")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO [Reverse Engineering] generate columns from DB
}