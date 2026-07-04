package ar.edu.utn.frba.ddsi.clima.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.jspecify.annotations.NonNull;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class weatherAPIConfig {
    @Value("${weather.api-key}") private String apiKey;
    private final String location="BUENOS AIRES";

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://api.weatherapi.com/v1")
                .requestInterceptor((request, body, execution) -> {
                    URI uriConApiKey = UriComponentsBuilder.fromUri(request.getURI())
                            .queryParam("key", apiKey)
                            .queryParam("q", location)
                            .queryParam("aqi", "no")
                            .encode()
                            .build()
                            .toUri();

                    return execution.execute(new HttpRequestWrapper(request) {
                        @Override
                        @NonNull
                        public URI getURI() {
                            return uriConApiKey;
                        }
                    }, body);
                })
                .build();
    }
}

