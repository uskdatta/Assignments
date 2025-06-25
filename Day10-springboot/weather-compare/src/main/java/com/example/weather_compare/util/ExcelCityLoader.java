package com.example.weather_compare.util;

import com.example.weather_compare.model.Coordinate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads cities.xlsx (columns: City | Latitude | Longitude) into memory once.
 */
@Component
public class ExcelCityLoader {

    private final Map<String, Coordinate> cache = new HashMap<>();

    public Coordinate get(String city) {
        return cache.get(city.trim().toLowerCase());
    }

    @PostConstruct
    void load() {
        try (InputStream in = new ClassPathResource("cities.xlsx").getInputStream();
             XSSFWorkbook wb = new XSSFWorkbook(in)) {

            wb.getSheetAt(0).forEach(row -> {
                if (row.getRowNum() == 0) return;          // skip header
                String name  = getString(row, 0);
                double lat   = getNumeric(row, 1);
                double lon   = getNumeric(row, 2);
                cache.put(name.trim().toLowerCase(), new Coordinate(lat, lon));
            });
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load cities.xlsx", e);
        }
    }

    private String getString(Row r, int idx) {
        return r.getCell(idx).getStringCellValue();
    }
    private double getNumeric(Row r, int idx) {
        return r.getCell(idx).getNumericCellValue();
    }
}
