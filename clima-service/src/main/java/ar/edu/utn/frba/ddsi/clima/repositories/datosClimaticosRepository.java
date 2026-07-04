package ar.edu.utn.frba.ddsi.clima.repositories;

import ar.edu.utn.frba.ddsi.clima.dto.weatherApiResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class datosClimaticosRepository {
    private final List<weatherApiResponseDTO> historial = new CopyOnWriteArrayList<>();

    public void guardar(weatherApiResponseDTO datos) {
        if (datos != null) {
            this.historial.add(datos);
        }
    }

    public weatherApiResponseDTO obtenerUltimo() {
        if (historial.isEmpty()) {
            return null;
        }
        return historial.getLast();
    }
}
