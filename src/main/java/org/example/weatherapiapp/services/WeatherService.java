package org.example.weatherapiapp.services;

import java.time.LocalDateTime;
import java.util.Optional;
import org.example.weatherapiapp.dto.WeatherResponseDto;
import org.example.weatherapiapp.entities.Weather;
import org.example.weatherapiapp.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.weatherRepository = weatherRepository;
    }

    public Optional<Weather> getWeatherByCityName(String cityName) {
        try {
            String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + cityName;
            WeatherResponseDto response = restTemplate.getForObject(url, WeatherResponseDto.class);

            if (response == null || response.getLocation() == null || response.getCurrent() == null) {
                return Optional.empty();
            }

            Weather weather = new Weather();
            mapResponseToWeather(response, weather);

            return Optional.of(weatherRepository.save(weather));
        } catch (RestClientException e) {

            return Optional.empty();
        }
    }

    private void mapResponseToWeather(WeatherResponseDto response, Weather weather) {
        weather.setCityName(response.getLocation().getName());
        weather.setRegion(response.getLocation().getRegion());
        weather.setCountry(response.getLocation().getCountry());
        weather.setLatitude(response.getLocation().getLat());
        weather.setLongitude(response.getLocation().getLon());
        weather.setTemperature(response.getCurrent().getTemp_c());
        weather.setWeatherCondition(response.getCurrent().getCondition().getText());
        weather.setLastUpdated(LocalDateTime.now());
    }

    // Additional methods if needed
}

