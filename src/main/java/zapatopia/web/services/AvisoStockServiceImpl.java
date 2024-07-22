package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.AvisoStockJpa;
import zapatopia.web.repository.AvisoStockRepository;

import java.util.List;

@Service
public class AvisoStockServiceImpl implements AvisoStockService {

    @Autowired
    private AvisoStockRepository stockRepository;

//    @Autowired
//    private JavaMailSender mailSender;

    @Override
    public AvisoStockJpa subscribeToStock(AvisoStockJpa stock) {
        return stockRepository.save(stock);
    }

    @Override
    public void notifySubscribers(long productId) {
        List<AvisoStockJpa> subscriptions = stockRepository.findAllByProductId(productId);
        for (AvisoStockJpa subscription : subscriptions) {
            sendEmail(subscription.getEmail(), "Producto Nuevamente con Stock", "El producto al que te suscribiste ha vuelto a tener stock.");
        }
    }

    @Override
    public List<AvisoStockJpa> getAllSubscriptions() {
        return (List<AvisoStockJpa>) stockRepository.findAll();
    }

    private void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
        System.out.println("Correo envio a" + to);
    }
}
