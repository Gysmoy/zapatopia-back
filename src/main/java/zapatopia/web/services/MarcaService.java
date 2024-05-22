package zapatopia.web.services;

import zapatopia.web.jpa.MarcaJpa;

import java.util.List;

public interface MarcaService {
    List<MarcaJpa> obtenerMarcas();
    MarcaJpa obtenerMarca(long id);
}
