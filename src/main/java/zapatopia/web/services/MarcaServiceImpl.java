package zapatopia.web.services;

import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.repository.MarcaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    final Logger logger = LoggerFactory.getLogger(MarcaServiceImpl.class);


    @Override
    public List<MarcaJpa> obtenerMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public MarcaJpa obtenerMarca(long id) {
        return marcaRepository.findById(id).isPresent() ? marcaRepository.findById(id).get() : null;
    }

    @Override
    public MarcaJpa guardarMarca(MarcaJpa marca) {
        if (marca.getId() == 0) {
            logger.info("Creando nueva marca");
            marca.setFechaCreacion(LocalDateTime.now());
        }
        marca.setFechaModificacion(LocalDateTime.now());
        return marcaRepository.save(marca);
    }

    @Override
    public void eliminarMarca(long id) {
        marcaRepository.deleteById(id);
    }
}
