package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.jpa.StockJpa;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<MainResponse> subscribeToStock(
            @RequestBody StockJpa stock
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            StockJpa savedStock = stockService.subscribeToStock(stock);
            response.setStatus(200);
            response.setMessage("Suscrito existosamente");
            response.setData(savedStock);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public ResponseEntity<MainResponse> notifySubscribers(
            @RequestParam("productId") long productId
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            stockService.notifySubscribers(productId);
            response.setStatus(200);
            response.setMessage("Suscriptores notificados exitosamente");
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> getAllSubscriptions() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<StockJpa> subscriptions = stockService.getAllSubscriptions();
            response.setStatus(200);
            response.setMessage("Operación exitosamente");
            response.setData(subscriptions);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
