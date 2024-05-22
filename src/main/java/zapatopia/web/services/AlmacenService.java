package zapatopia.web.services;

import zapatopia.web.jpa.AlmacenJpa;

import java.util.List;

public interface AlmacenService {
    List<AlmacenJpa> obtenerAlmacenes();
    AlmacenJpa obtenerAlmacen(long id);
    AlmacenJpa guardarAlmacen(AlmacenJpa almacen);
    void eliminarAlmacen(long id);
}
