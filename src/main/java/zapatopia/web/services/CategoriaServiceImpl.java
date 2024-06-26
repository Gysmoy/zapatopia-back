package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.repository.CategoriaRepository;
import zapatopia.web.repository.MarcaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaJpa> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaJpa obtenerCategoria(long id) {
        return categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
    }

    @Override
    public CategoriaJpa guardarCategoria(CategoriaJpa categoria) {
        if (categoria.getId() == 0) {
            categoria.setFechaCreacion(LocalDateTime.now());
        }
        categoria.setFechaModificacion(LocalDateTime.now());
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(long id) {
        categoriaRepository.deleteById(id);
    }
}
