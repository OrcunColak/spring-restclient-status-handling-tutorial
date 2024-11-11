package com.colak.springtutorial.service.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestClientService {

    private final RestClient restClient;

    record GeoData(double lat, double lon) {
    }

    public void getString(String url) {
        List<GeoData> geoData = restClient.get()
                .uri("https://api.openweathermap.org")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            // Convert status code and http headers to string
                            String message = String.format("Status Code %d Response Headers %s",
                                    response.getStatusCode().value(),
                                    response.getHeaders());

                            throw new RuntimeException(message);
                        })
                .body(new ParameterizedTypeReference<>() {
                });
    }

}
