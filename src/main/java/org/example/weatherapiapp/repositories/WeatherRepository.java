package org.example.weatherapiapp.repositories;

import org.example.weatherapiapp.entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
