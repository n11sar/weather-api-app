package org.example.weatherapiapp.components;

import java.util.Arrays;
import java.util.List;
import org.example.weatherapiapp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataScheduler {

    private final WeatherService weatherService;

    @Autowired
    public WeatherDataScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 3600000)
    public void refreshWeatherData() {
        List<String> cityNames = getCityNames();

        for (String cityName : cityNames) {
            weatherService.getWeatherByCityName(cityName);
        }
    }

    private List<String> getCityNames() {
        return Arrays.asList("London", "New York", "Tokyo");
    }
}
