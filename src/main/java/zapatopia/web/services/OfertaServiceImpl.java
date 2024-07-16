package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.EnvioCorreo;
import zapatopia.web.dto.OfertaDto;
import zapatopia.web.dto.ProductoVentaDto;
import zapatopia.web.jpa.ClienteJpa;
import zapatopia.web.jpa.OfertaJpa;
import zapatopia.web.jpa.ProductoJpa;
import zapatopia.web.repository.ClienteRepository;
import zapatopia.web.repository.OfertaRepository;
import zapatopia.web.repository.ProductoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<OfertaDto> listarOfertas() {

        List<OfertaDto> ofertas = new ArrayList<>();
        ofertaRepository.findAll().forEach(oferta -> {
            ProductoJpa productoJpa = oferta.getProducto();
            ProductoVentaDto productoVentaDto = new ProductoVentaDto();
            productoVentaDto.setId(productoJpa.getId());
            productoVentaDto.setNombre(productoJpa.getNombre());

            OfertaDto ofertaDto = new OfertaDto();
            ofertaDto.setId(oferta.getId());
            ofertaDto.setProducto(productoVentaDto);
            ofertaDto.setCantidadEntregar(oferta.getCantidadEntregar());
            ofertaDto.setCantidadComprar(oferta.getCantidadComprar());
            ofertaDto.setFechaModificacion(oferta.getFechaModificacion());

            ofertas.add(ofertaDto);
        });

        return ofertas;
    }

    @Override
    public OfertaDto crearOferta(OfertaDto oferta) {

        ProductoJpa productoJpa = productoRepository.findById(oferta.getProducto().getId()).orElseThrow();

        OfertaJpa ofertaJpa = new OfertaJpa();
        ofertaJpa.setProducto(productoJpa);
        ofertaJpa.setCantidadEntregar(oferta.getCantidadEntregar());
        ofertaJpa.setCantidadComprar(oferta.getCantidadComprar());
        ofertaJpa.setFechaCreacion(LocalDateTime.now());
        ofertaJpa.setFechaModificacion(LocalDateTime.now());

        ofertaRepository.save(ofertaJpa);
        oferta.setId(ofertaJpa.getId());

        if(oferta.isEnvioAutomatico()) {
            enviarCorreoMasivo(ofertaJpa);
        }

        return oferta;
    }

    @Override
    public void enviarCorreoMasivo(Long idOferta) {
        OfertaJpa ofertaJpa = ofertaRepository.findById(idOferta).orElseThrow();
        enviarCorreoMasivo(ofertaJpa);
    }

    private void enviarCorreoMasivo(OfertaJpa ofertaJpa) {
        List<ClienteJpa> clientes = clienteRepository.listarNotificados();
        List<EnvioCorreo> listaEnvio = new ArrayList<>();
        clientes.forEach(cliente -> {
            String destino = cliente.getPersona().getCorreoElectronico();
            String asunto = "¡Oferta Especial en Zapatopia!";
            Map<String, Object> variables = new HashMap<>();
            variables.put("nombre", cliente.getPersona().getNombres());
            variables.put("cantidadComprar", ofertaJpa.getCantidadComprar());
            variables.put("cantidadEntregar", ofertaJpa.getCantidadEntregar());
            variables.put("nombreProducto", ofertaJpa.getProducto().getNombre());
            Map<String, String> imagenesAdjuntas = new HashMap<>();
            imagenesAdjuntas.put("imagenProducto", ofertaJpa.getProducto().getPathFoto().replace("images", "img").substring(1));
            String plantilla = "oferta";
            EnvioCorreo envioCorreo = new EnvioCorreo(destino,asunto, variables, imagenesAdjuntas, null, plantilla);
            listaEnvio.add(envioCorreo);
        });
        emailService.enviarCorreoAsync(listaEnvio);
    }

    @Override
    public void eliminarOferta(long id) {
        ofertaRepository.deleteById(id);
    }
}
