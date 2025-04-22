# Read me

The original idea is from  
https://blog.stackademic.com/fluent-api-integrations-using-restclient-api-in-spring-6-b9122bfea4ed

It shows how to handle errors
```java
// RestClient throws a subclass of RestClientException when it receives a response with a 4xx or 5xx status code.
// This behavior can be modified using the onStatus method.

WeatherData weather = restClient.get()
        .uri(weatherUrl)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, 
          (request, response) -> { 
            throw new WeatherNotAvailableException(response.getStatusCode(), response.getHeaders());
          })
        .body(WeatherData.class);
```
