package com.example.weather_compare.model;

import java.time.LocalDate;

public class WeatherForecast {

    private LocalDate date;
    private double temperature;   // °C (daily avg)
    private double humidity;      // % (daily avg)
    private double windSpeed;     // km/h (daily avg)

    // getters & setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
}
