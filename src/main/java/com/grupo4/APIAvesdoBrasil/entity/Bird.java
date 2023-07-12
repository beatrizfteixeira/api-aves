package com.grupo4.APIAvesdoBrasil.entity;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="birds")
public class Bird {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="commonName")
    private String commonName;
    @Column(name="scientificName")
    private String scientificName;
    @Column(name="description")
    private String description;

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Bird() {

    }

    public Bird(int id, String commonName, String scientificName, String description) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
    }

    public Bird(String commonName, String scientificName, String description) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getDescription() {
        return description;
    }
}
