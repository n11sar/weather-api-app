package org.example.weatherapiapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;


@Entity
@Table(name = "weather_data")
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
    private Double temperature;
    private String weatherCondition;
    private LocalDateTime lastUpdated;

    // Getters and setters
}

