package zapatopia.web.services;

import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.jpa.MarcaJpa;

import java.util.List;

public interface MarcaService {
    List<MarcaJpa> obtenerMarcas();
    MarcaJpa obtenerMarca(long id);
    MarcaJpa guardarMarca(MarcaJpa marca);
    void eliminarMarca(long id);
}
