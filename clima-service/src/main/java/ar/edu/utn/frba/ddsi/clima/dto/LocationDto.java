package ar.edu.utn.frba.ddsi.clima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationDto(
        String name,
        String region,
        String country,
        double lat,
        double lon,
        @JsonProperty("tz_id") String tzId,
        @JsonProperty("localtime_epoch") long localtimeEpoch,
        String localtime
) {}
