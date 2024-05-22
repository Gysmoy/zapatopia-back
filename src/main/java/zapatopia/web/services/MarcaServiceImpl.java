package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.repository.MarcaRepository;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<MarcaJpa> obtenerMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public MarcaJpa obtenerMarca(long id) {
        return marcaRepository.findById(id).isPresent() ? marcaRepository.findById(id).get() : null;
    }
}
