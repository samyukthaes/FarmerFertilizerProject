package com.ust.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fertilizer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int fertilizerId;
    private String fertilizerName;
    private double cost;

    private Integer quantity;
}
