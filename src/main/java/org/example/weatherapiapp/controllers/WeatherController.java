package org.example.weatherapiapp.controllers;

import java.util.Optional;
import org.example.weatherapiapp.entities.Weather;
import org.example.weatherapiapp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<Object> getWeatherByCityName(@PathVariable String cityName) {
        Optional<Weather> weatherOpt = weatherService.getWeatherByCityName(cityName);

        return weatherOpt.<ResponseEntity<Object>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid or not found city name: " + cityName));

    }

    // Additional endpoints can be added here
}
