package org.example.weatherapiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDto {
    private LocationDto location;
    private CurrentWeatherDto current;
}
