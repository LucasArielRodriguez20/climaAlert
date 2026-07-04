package ar.edu.utn.frba.ddsi.clima.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class weatherApiResponseDTO {
    LocationDto location;
    CurrentDTO current;
}

