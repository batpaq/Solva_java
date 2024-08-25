package com.batpaq.spring.springboot.solva.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Value("${currency.api.url}")
    private String apiUrl;

    @Value("${currency.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getExchangeRate(String fromCurrency, String toCurrency) {
        String url = String.format("%s?apikey=%s&base=%s&symbols=%s", apiUrl, apiKey, fromCurrency, toCurrency);
        return restTemplate.getForObject(url, String.class);
    }
}