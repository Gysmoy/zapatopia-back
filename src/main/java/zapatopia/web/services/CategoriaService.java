package zapatopia.web.services;

import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.jpa.MarcaJpa;

import java.util.List;

public interface CategoriaService {
    List<CategoriaJpa> obtenerCategorias();
    CategoriaJpa obtenerCategoria(long id);
    CategoriaJpa guardarCategoria(CategoriaJpa categoria);
    void eliminarCategoria(long id);
}
