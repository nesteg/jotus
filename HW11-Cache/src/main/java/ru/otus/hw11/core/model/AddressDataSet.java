package ru.otus.hw11.core.model;

import javax.persistence.*;


@Entity
@Table(name = "addresses")
public class AddressDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;

    @Column(name = "street")
    private String street;

    public AddressDataSet(){

    }

    public AddressDataSet(long id,String street) {
        this.id = id;
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }

}
