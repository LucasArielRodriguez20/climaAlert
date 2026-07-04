package ar.edu.utn.frba.ddsi.clima.schedulers;

import ar.edu.utn.frba.ddsi.clima.dto.weatherApiResponseDTO;
import ar.edu.utn.frba.ddsi.clima.repositories.datosClimaticosRepository;
import ar.edu.utn.frba.ddsi.clima.services.EmailService;
import ar.edu.utn.frba.ddsi.clima.services.alertaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {
    private final alertaService climaService;
    private final datosClimaticosRepository datosClimaticosRepository;
    private final EmailService emailService;


    public ClimaScheduler(alertaService climaService, datosClimaticosRepository datosClimaticosRepository, EmailService emailService) {
        this.climaService = climaService;
        this.datosClimaticosRepository = datosClimaticosRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 300000)
    public void tareaDescarga() {
        climaService.descargarDatos();
    }

    @Scheduled(initialDelay = 6000,fixedRate = 60000)
    public void tareaAnalisis() {
        weatherApiResponseDTO datos=datosClimaticosRepository.obtenerUltimo();

        if (datos == null) {
            System.out.println("LOG: El repositorio aún no tiene datos. Esperando siguiente ciclo...");
            return;
        }

        if (climaService.evaluarAlertaMeteorologica(datos) ==1)
        {
            emailService.generarAlerta(datos);
            System.out.println("Se ha generado una alerta");
        }
        System.out.println("Condiciones climáticas normales no se ha generado ninguna alerta");
    }
}
