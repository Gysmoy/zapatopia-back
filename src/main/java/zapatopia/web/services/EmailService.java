package zapatopia.web.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import zapatopia.web.dto.EnvioCorreo;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void enviarCorreo(EnvioCorreo envioCorreo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariables(envioCorreo.getVariables());
        String htmlContent = templateEngine.process(envioCorreo.getPlantilla(), context);

        helper.setTo(envioCorreo.getDestino());
        helper.setSubject(envioCorreo.getAsunto());
        helper.setText(htmlContent, true);

        ClassPathResource logo = new ClassPathResource("static/zapatopia.png");
        helper.addInline("zapatopiaLogo", logo, "image/png");

        Map<String, String> imagenes = envioCorreo.getImagenesAdjuntas();
        imagenes.forEach((key, value) -> {
            FileSystemResource res = new FileSystemResource(new File(value));
            try {
                helper.addInline(key, res);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        mailSender.send(message);
        log.info("correo enviado exitosamente {}", envioCorreo);
    }

    @Async
    public void enviarCorreoAsync(EnvioCorreo envioCorreo) {
        try {
            enviarCorreo(envioCorreo);
        } catch (MessagingException e) {
            log.error("Error al enviar correo: ", e);
        }
    }

    @Async
    public void enviarCorreoAsync(List<EnvioCorreo> listaEnvio) {
        listaEnvio.forEach(correo -> {
            try {
                enviarCorreo(correo);
            } catch (MessagingException e) {
                log.error("Error al enviar correo: ", e);
            }
        });
    }

}
