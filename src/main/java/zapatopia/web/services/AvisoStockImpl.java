package zapatopia.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.StockJpa;
import zapatopia.web.repository.StockRepository;
import zapatopia.web.services.StockService;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public StockJpa subscribeToStock(StockJpa stock) {
        return stockRepository.save(stock);
    }

    @Override
    public void notifySubscribers(long productId) {
        List<StockJpa> subscriptions = stockRepository.findAllByProductId(productId);
        for (StockJpa subscription : subscriptions) {
            sendEmail(subscription.getEmail(), "Product Back in Stock", "The product you subscribed to is back in stock.");
        }
    }

    @Override
    public List<StockJpa> getAllSubscriptions() {
        return (List<StockJpa>) stockRepository.findAll();
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
