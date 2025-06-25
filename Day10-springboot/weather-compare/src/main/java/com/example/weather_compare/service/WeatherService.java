package com.example.weather_compare.service;

import com.example.weather_compare.model.Coordinate;
import com.example.weather_compare.model.WeatherForecast;
import com.example.weather_compare.util.ExcelCityLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class WeatherService {

    private final RestTemplate rest = new RestTemplate();
    private final ExcelCityLoader cityLoader;

    public WeatherService(ExcelCityLoader cityLoader) {
        this.cityLoader = cityLoader;
    }

    /** 7-day forecast for a city name in cities.xlsx */
    public List<WeatherForecast> getForecast(String city) {
        Coordinate c = cityLoader.get(city);
        if (c == null) throw new IllegalArgumentException("City not found: " + city);

        String url = """
                https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f
                &hourly=temperature_2m,relativehumidity_2m,windspeed_10m&forecast_days=7&timezone=auto
                """.replace("\n","").formatted(c.latitude(), c.longitude());

        Map<?,?> json = rest.getForObject(url, Map.class);

        Map<?,?> hourly = (Map<?,?>) json.get("hourly");
        List<String> times = (List<String>) hourly.get("time");
        List<Double> temp  = (List<Double>) hourly.get("temperature_2m");
        List<Double> hum   = (List<Double>) hourly.get("relativehumidity_2m");
        List<Double> wind  = (List<Double>) hourly.get("windspeed_10m");

        Map<LocalDate, List<Integer>> indexByDay = new HashMap<>();
        for (int i = 0; i < times.size(); i++) {
            LocalDate day = LocalDate.parse(times.get(i).substring(0, 10));
            indexByDay.computeIfAbsent(day, k -> new ArrayList<>()).add(i);
        }

        List<WeatherForecast> result = new ArrayList<>();
        indexByDay.keySet().stream().sorted().forEach(d -> {
            List<Integer> idx = indexByDay.get(d);
            double t = avg(temp , idx);
            double h = avg(hum  , idx);
            double w = avg(wind , idx);

            WeatherForecast wf = new WeatherForecast();
            wf.setDate(d);
            wf.setTemperature(t);
            wf.setHumidity(h);
            wf.setWindSpeed(w);
            result.add(wf);
        });

        return result;
    }

    private double avg(List<Double> list, List<Integer> idx) {
        return idx.stream().mapToDouble(list::get).average().orElse(Double.NaN);
    }

    /** returns map: date -> comparison string */
    public Map<LocalDate, String> compare(String cityA, String cityB) {
        List<WeatherForecast> a = getForecast(cityA);
        List<WeatherForecast> b = getForecast(cityB);

        Map<LocalDate, String> out = new LinkedHashMap<>();
        for (int i = 0; i < a.size(); i++) {
            WeatherForecast fa = a.get(i);
            WeatherForecast fb = b.get(i);

            String line = String.format(
                    "%s: %.1f °C vs %.1f °C | %.0f%% vs %.0f%% RH | %.1f km/h vs %.1f km/h wind",
                    fa.getDate(), fa.getTemperature(), fb.getTemperature(),
                    fa.getHumidity(),    fb.getHumidity(),
                    fa.getWindSpeed(),   fb.getWindSpeed()
            );
            out.put(fa.getDate(), line);
        }
        return out;
    }
}
