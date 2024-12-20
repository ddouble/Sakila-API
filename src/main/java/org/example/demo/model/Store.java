package org.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "store", schema = "sakila")
public class Store {
    @Id
    @Column(name = "store_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @OneToMany(mappedBy = "store")
    private Set<Inventory> inventories = new LinkedHashSet<>();

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

}