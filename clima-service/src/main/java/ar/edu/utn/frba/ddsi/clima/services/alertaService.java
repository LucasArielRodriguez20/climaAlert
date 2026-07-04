package ar.edu.utn.frba.ddsi.clima.services;

import ar.edu.utn.frba.ddsi.clima.dto.weatherApiResponseDTO;
import ar.edu.utn.frba.ddsi.clima.repositories.datosClimaticosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class alertaService {
    private final RestClient restClient;
    private final datosClimaticosRepository datosClimaticosRepository;

    public alertaService(RestClient restClient, datosClimaticosRepository datosClimaticosRepository) {
        this.restClient = restClient;
        this.datosClimaticosRepository = datosClimaticosRepository;
    }

    public weatherApiResponseDTO getDatos() {
        return restClient.get()
                .uri("/current.json")
                .retrieve()
                .body(weatherApiResponseDTO.class);
    }

    public void descargarDatos(){
        try{
            weatherApiResponseDTO datos = getDatos();


            if (datos != null) {
                datosClimaticosRepository.guardar(datos);
                System.out.println("LOG: Datos guardados con éxito en el registro histórico.");
                System.out.println("humedad que hay actual "+datos.getCurrent().humidity());
                System.out.println("temperatura que hay actual "+datos.getCurrent().tempC());
            }

        } catch (Exception e) {
            System.err.println("Error al conectar con la API de clima: " + e.getMessage());
        }
    }



    public int evaluarAlertaMeteorologica(weatherApiResponseDTO datos) {

        double temperatura = datos.getCurrent().tempC();
        int humedad = datos.getCurrent().humidity();
            //humedad+=80;
            //temperatura+=40;
        if (temperatura > 35 && humedad > 60) {
            return 1;
        } else  {
            return 0;
        }
    }
}
