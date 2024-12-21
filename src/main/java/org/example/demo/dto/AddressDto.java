package org.example.demo.dto;

import org.example.demo.model.Address;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Address}
 */
public class AddressDto implements Serializable {
    private final Integer id;
    private final String address;
    private final String address2;
    private final String district;
    private final String postalCode;
    private final String phone;
    private final Instant lastUpdate;
    private final CityDto city;

    public AddressDto(Integer id, String address, String address2, String district, String postalCode, String phone, Instant lastUpdate,
                      CityDto city) {
        this.id = id;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

//    public String getAddress2() {
//        return address2;
//    }

    public String getDistrict() {
        return district;
    }

//    public String getPostalCode() {
//        return postalCode;
//    }

//    public String getPhone() {
//        return phone;
//    }

//    public Instant getLastUpdate() {
//        return lastUpdate;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto entity = (AddressDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.address2, entity.address2) &&
                Objects.equals(this.district, entity.district) &&
                Objects.equals(this.postalCode, entity.postalCode) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.lastUpdate, entity.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, address2, district, postalCode, phone, lastUpdate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "address = " + address + ", " +
                "address2 = " + address2 + ", " +
                "district = " + district + ", " +
                "postalCode = " + postalCode + ", " +
                "phone = " + phone + ", " +
                "lastUpdate = " + lastUpdate + ")";
    }

    public CityDto getCity() {
        return city;
    }
}