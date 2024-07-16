package zapatopia.web.services;

import zapatopia.web.dto.OfertaDto;

import java.util.List;

public interface OfertaService {
    List<OfertaDto> listarOfertas();
    OfertaDto crearOferta(OfertaDto oferta);
    void enviarCorreoMasivo(Long idOferta);
    void eliminarOferta(long id);
}
