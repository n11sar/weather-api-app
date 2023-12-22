package org.example.weatherapiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    private String text;
    private String icon;
    private int code;

}
