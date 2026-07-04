package ar.edu.utn.frba.ddsi.clima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentDTO(
        @JsonProperty("last_updated_epoch") long lastUpdatedEpoch,
        @JsonProperty("last_updated") String lastUpdated,
        @JsonProperty("temp_c") double tempC,
        @JsonProperty("temp_f") double tempF,
        @JsonProperty("is_day") int isDay,
        ConditionDTO condition,
        int humidity,
        @JsonProperty("will_it_rain") int willItRain,
        @JsonProperty("chance_of_rain") int chanceOfRain,
        @JsonProperty("will_it_snow") int willItSnow,
        @JsonProperty("chance_of_snow") int chanceOfSnow
) {}