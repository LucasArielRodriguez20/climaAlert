package ar.edu.utn.frba.ddsi.clima.services;

import ar.edu.utn.frba.ddsi.clima.dto.weatherApiResponseDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

        private final JavaMailSender mailSender;

        private final String[] destinatarios = {
                "admin@clima.com",
                "emergencias@clima.com",
                "meteorologia@clima.com"
        };

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

        public void generarAlerta(weatherApiResponseDTO clima) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();

            mensaje.setTo(destinatarios);
            mensaje.setSubject("⚠️ ALERTA METEOROLÓGICA CRÍTICA DETECTADA");

            String cuerpo = String.format(
                    "Se han detectado condiciones climáticas críticas.\n\n" +
                            "DETALLE COMPLETO DEL CLIMA:\n" +
                            "----------------------------------------\n" +
                            "Ciudad: %s\n" +
                            "Región: %s\n" +
                            "País: %s\n" +
                            "----------------------------------------\n" +
                            "Temperatura Actual: %.1f °C\n" +
                            "Humedad: %d%%\n" +
                            "Condición: %s\n" +
                            "----------------------------------------\n" +
                            "Fecha de reporte de la API: %s\n",
                    clima.getLocation().name(),
                    clima.getLocation().region(),
                    clima.getLocation().country(),
                    clima.getCurrent().tempC(),
                    clima.getCurrent().humidity(),
                    clima.getCurrent().condition().text(),
                    clima.getCurrent().lastUpdated()
            );

            mensaje.setText(cuerpo);

            mailSender.send(mensaje);
            System.out.println("[Email]: Alerta enviada con éxito a los comités correspondientes.");

        } catch (Exception e) {
            System.err.println("Error crítico al intentar enviar los correos de alerta: " + e.getMessage());
        }
    }
}
