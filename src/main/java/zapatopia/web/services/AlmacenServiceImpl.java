package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.AlmacenJpa;
import zapatopia.web.repository.AlmacenRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    public List<AlmacenJpa> obtenerAlmacenes() {
        return almacenRepository.findAll();
    }

    @Override
    public AlmacenJpa obtenerAlmacen(long id) {
        return almacenRepository.findById(id).isPresent() ? almacenRepository.findById(id).get() : null;
    }

    @Override
    public AlmacenJpa guardarAlmacen(AlmacenJpa almacen) {
        if (almacen.getId() == 0) {
            almacen.setFechaCreacion(LocalDateTime.now());
        }
        almacen.setFechaModificacion(LocalDateTime.now());
        return almacenRepository.save(almacen);
    }

    @Override
    public void eliminarAlmacen(long id) {
        almacenRepository.deleteById(id);
    }
}
