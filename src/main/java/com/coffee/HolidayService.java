package com.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HolidayService {

    private static final String HOLIDAY_API_URL = "https://date.nager.at/api/v3/PublicHolidays/{year}/{countryCode}";
    @Autowired
    private final RestTemplate restTemplate;

    public HolidayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("holidays")
    public List<Holiday> getHolidays(int year, String country) {
        String url = String.format("https://date.nager.at/api/v3/PublicHolidays/%d/%s", year, country);
        ResponseEntity<List<Holiday>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Holiday>>() {}
        );
        return response.getBody();
    }
}
