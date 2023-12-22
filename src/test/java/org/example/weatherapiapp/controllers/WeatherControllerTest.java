package org.example.weatherapiapp.controllers;

import java.time.LocalDateTime;
import java.util.Optional;
import org.example.weatherapiapp.entities.Weather;
import org.example.weatherapiapp.services.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class WeatherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    void getWeatherByCityName_ReturnsWeather() throws Exception {
        // Подготовка
        String cityName = "TestCity";
        Weather mockWeather = createMockWeather(cityName);

        when(weatherService.getWeatherByCityName(cityName)).thenReturn(Optional.of(mockWeather));

        // Выполнение и Проверка
        mockMvc.perform(get("/weather/city/" + cityName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cityName").value(cityName));
        // Дополнительные проверки...
    }

    private Weather createMockWeather(String cityName) {
        Weather weather = new Weather();
        weather.setCityName(cityName);
        weather.setRegion("TestRegion");
        weather.setCountry("TestCountry");
        weather.setLatitude(10.0);
        weather.setLongitude(20.0);
        weather.setTemperature(25.0);
        weather.setWeatherCondition("Sunny");
        weather.setLastUpdated(LocalDateTime.now());
        return weather;
    }
}

