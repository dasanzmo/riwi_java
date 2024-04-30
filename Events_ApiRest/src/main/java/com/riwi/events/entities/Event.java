package com.riwi.events.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name="event")
@Data //anotacion para getters and setters
@AllArgsConstructor //anotacion para contructor lleno
@NoArgsConstructor//anotacion para constructor vacio

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Date fecha;
    private String ubicacion;
    private int capacidad;
    

    
}
