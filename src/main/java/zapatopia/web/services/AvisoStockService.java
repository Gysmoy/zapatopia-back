package zapatopia.web.services;

import zapatopia.web.jpa.AvisoStockJpa;
import zapatopia.web.jpa.StockJpa;

import java.util.List;

public interface AvisoStockService {
    AvisoStockJpa subscribeToStock(AvisoStockJpa stock);
    void notifySubscribers(long productId);
    List<AvisoStockJpa> getAllSubscriptions();
}
