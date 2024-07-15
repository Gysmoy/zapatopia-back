package zapatopia.web.services;

import zapatopia.web.jpa.StockJpa;

import java.util.List;

public interface StockService {
    StockJpa subscribeToStock(StockJpa stock);
    void notifySubscribers(long productId);
    List<StockJpa> getAllSubscriptions();
}
