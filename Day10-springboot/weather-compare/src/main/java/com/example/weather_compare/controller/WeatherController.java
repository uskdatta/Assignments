package com.example.weather_compare.controller;

import com.example.weather_compare.model.WeatherForecast;
import com.example.weather_compare.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    /** GET /api/weather/{city} */
    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherForecast>> forecast(@PathVariable String city) {
        return ResponseEntity.ok(service.getForecast(city));
    }

    /** GET /api/weather/compare?city1=Paris&city2=Berlin */
    @GetMapping("/compare")
    public ResponseEntity<Map<LocalDate, String>> compare(
            @RequestParam String city1,
            @RequestParam String city2) {
        return ResponseEntity.ok(service.compare(city1, city2));
    }
}
