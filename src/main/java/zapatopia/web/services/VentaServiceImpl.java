package zapatopia.web.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.*;
import zapatopia.web.jpa.*;
import zapatopia.web.repository.ClienteRepository;
import zapatopia.web.repository.EstadoRepository;
import zapatopia.web.repository.PersonaRepository;
import zapatopia.web.repository.StockRepository;
import zapatopia.web.repository.VentaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public VentaDto generarVenta(VentaDto venta) {

        log.info("Venta ingresada: {}", venta);
        LocalDateTime dateTime = LocalDateTime.now();

        ClienteDto clienteDto = venta.getCliente();


        PersonaJpa personaEntity = personaRepository
                .findByTipoDocumentoAndNumeroDocumento(clienteDto.getTipoDocumento(), clienteDto.getNumeroDocumento())
                .map(persona -> setPersonaJpa(persona, clienteDto, dateTime))
                .orElse(getPersonaJpa(clienteDto, dateTime));

        personaRepository.save(personaEntity);


        ClienteJpa clienteEntity = new ClienteJpa();
        clienteEntity.setPersona(personaEntity);
        clienteEntity.setFlagNotificar(clienteDto.isFlagNotificar()? "S" : "N");
        clienteEntity.setMedioPreferido(clienteDto.getMedioPreferido());
        clienteEntity.setFechaCreacion(dateTime);
        clienteEntity.setFechaModificacion(dateTime);

        clienteRepository.save(clienteEntity);


        VentaJpa ventaEntity = new VentaJpa();
        ventaEntity.setCodigoPedido(generarCodigoPedido());
        ventaEntity.setCliente(clienteEntity);
        ventaEntity.setEstado(estadoRepository.findByEstado("PENDIENTE").orElse(null));
        ventaEntity.setFechaCreacion(dateTime);
        ventaEntity.setFechaModificacion(dateTime);


        VentaJpa finalVentaEntity = ventaEntity;
        List<DetalleVentasJpa> detalleVentaEntity = venta.getDetalle().stream()
                .map(detalle -> {

                    ProductoVentaDto productoDto = detalle.getProducto();

                    StockJpa stockEntity = stockRepository.findById(productoDto.getIdStock())
                            .orElseThrow();

                    DetalleVentasJpa detalleEntity = new DetalleVentasJpa();
                    detalleEntity.setVenta(finalVentaEntity);
                    detalleEntity.setStock(stockEntity);
                    detalleEntity.setCantidad(detalle.getCantidad());
                    detalleEntity.setPrecio(detalle.getPrecio());
                    detalleEntity.setFechaCreacion(dateTime);
                    detalleEntity.setFechaModificacion(dateTime);

                    return detalleEntity;
                }).toList();

        ventaEntity.setDetalle(detalleVentaEntity);

        ventaEntity = ventaRepository.save(ventaEntity);


        enviarCorreoConfirmacion(ventaEntity);


        venta.setId(ventaEntity.getId());
        venta.setCodigoPedido(ventaEntity.getCodigoPedido());
        venta.setFechaModificacion(dateTime);

        return venta;
    }

    @Override
    public List<VentaDto> listarVentas() {
        List<VentaDto> ventas = new ArrayList<>();

        ventaRepository.findAll().forEach(venta -> {

            ClienteDto clienteDto = getClienteDto(venta);

            EstadoJpa estadoEntity = venta.getEstado();
            EstadoDto estadoDto = new EstadoDto();
            estadoDto.setId(estadoEntity.getId());
            estadoDto.setEstado(estadoEntity.getEstado());
            estadoDto.setDescripcion(estadoEntity.getDescripcion());

            VentaDto ventaDto = new VentaDto();
            ventaDto.setId(venta.getId());
            ventaDto.setCodigoPedido(venta.getCodigoPedido());
            ventaDto.setCliente(clienteDto);
            ventaDto.setEstado(estadoDto);
            ventaDto.setFechaModificacion(venta.getFechaModificacion());

            List<DetalleVentasJpa> detalleEntity = venta.getDetalle();
            List<DetalleVentaDto> detalle = detalleEntity.stream().map(d -> {

                ProductoJpa productoEntity = d.getStock().getProducto();

                ProductoVentaDto productoVentaDto = new ProductoVentaDto();
                productoVentaDto.setIdStock(d.getStock().getId());
                productoVentaDto.setId(productoEntity.getId());
                productoVentaDto.setCategoria(productoEntity.getCategoria());
                productoVentaDto.setMarca(productoEntity.getMarca());
                productoVentaDto.setNombre(productoEntity.getNombre());
                productoVentaDto.setDescripcion(productoEntity.getDescripcion());
                productoVentaDto.setPrecioVenta(productoVentaDto.getPrecioVenta());
                productoVentaDto.setTalla(productoVentaDto.getTalla());
                productoVentaDto.setColor(productoVentaDto.getColor());
                productoVentaDto.setGenero(productoVentaDto.getGenero());
                productoVentaDto.setPathFoto(productoVentaDto.getPathFoto());

                DetalleVentaDto detalleVentaDto = new DetalleVentaDto();
                detalleVentaDto.setId(d.getId());
                detalleVentaDto.setProducto(productoVentaDto);
                detalleVentaDto.setCantidad(d.getCantidad());
                detalleVentaDto.setPrecio(d.getPrecio());

                return detalleVentaDto;

            }).toList();

            ventaDto.setDetalle(detalle);
            ventas.add(ventaDto);
        });

        return ventas;
    }

    @Override
    public boolean actualizarEstado(Long idVenta, Long idEstado) {
        boolean update = ventaRepository.actualizarEstado(idVenta, idEstado) > 0;
        if(update) {
            enviarCorreoPedidoGestionado(idVenta);
        }
        return update;
    }

    private ClienteDto getClienteDto(VentaJpa venta) {
        ClienteJpa cliente = venta.getCliente();
        PersonaJpa persona = cliente.getPersona();

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setIdCliente(cliente.getId());
        clienteDto.setIdPersona(persona.getId());
        clienteDto.setTipoDocumento(persona.getTipoDocumento());
        clienteDto.setNumeroDocumento(persona.getNumeroDocumento());
        clienteDto.setNombres(persona.getNombres());
        clienteDto.setApellidoPaterno(persona.getApellidoPaterno());
        clienteDto.setApellidoMaterno(persona.getApellidoMaterno());
        clienteDto.setDireccion(persona.getDireccion());
        clienteDto.setCorreoElectronico(persona.getCorreoElectronico());
        clienteDto.setNumeroCelular(persona.getNumeroCelular());
        clienteDto.setFlagNotificar(cliente.getFlagNotificar().equals("S"));
        clienteDto.setMedioPreferido(cliente.getMedioPreferido());
        return clienteDto;
    }

    private void enviarCorreoConfirmacion(VentaJpa ventaEntity) {
        String destino = ventaEntity.getCliente().getPersona().getCorreoElectronico();
        String asunto = "Confirmación de Pedido";
        Map<String, Object> variables = new HashMap<>();
        variables.put("nombre", ventaEntity.getCliente().getPersona().getNombres());
        variables.put("codigoPedido", ventaEntity.getCodigoPedido());
        variables.put("detallePedido", ventaEntity.getDetalle());
        String plantilla = "confirmacionPedido";
        EnvioCorreo envioCorreo = new EnvioCorreo(destino,asunto, variables, plantilla);
        emailService.enviarCorreoAsync(envioCorreo);
    }

    private void enviarCorreoPedidoGestionado(Long idVenta) {
        VentaJpa ventaEntity = ventaRepository.findById(idVenta).orElseThrow();

        String estado = ventaEntity.getEstado().getEstado();

        String destino = ventaEntity.getCliente().getPersona().getCorreoElectronico();
        String asunto = "";
        Map<String, Object> variables = new HashMap<>();
        variables.put("nombre", ventaEntity.getCliente().getPersona().getNombres());
        variables.put("codigoPedido", ventaEntity.getCodigoPedido());
        String plantilla = "";

        if("PENDIENTE".equals(estado)) {
            asunto = "Confirmación de Pedido";
            plantilla = "confirmacionPedido";
        } else if ("PROCESADO".equals(estado)) {
            asunto = "Tu pedido está listo para recoger - Zapatopia";
            plantilla = "pedidoListo";
        } else if ("ENTREGADO".equals(estado)) {
            asunto = "Su pedido ha sido entregado";
            plantilla = "pedidoEntregado";
        }
        if(!plantilla.isEmpty()) {
            emailService.enviarCorreoAsync(new EnvioCorreo(destino,asunto, variables, plantilla));
        }

    }

    private PersonaJpa setPersonaJpa(PersonaJpa personaEntity, ClienteDto clienteDto, LocalDateTime dateTime) {
        personaEntity.setNombres(clienteDto.getNombres());
        personaEntity.setApellidoPaterno(clienteDto.getApellidoPaterno());
        personaEntity.setApellidoMaterno(clienteDto.getApellidoMaterno());
        personaEntity.setDireccion(clienteDto.getDireccion());
        personaEntity.setCorreoElectronico(clienteDto.getCorreoElectronico());
        personaEntity.setNumeroCelular(clienteDto.getNumeroCelular());
        personaEntity.setFechaCreacion(dateTime);
        personaEntity.setFechaModificacion(dateTime);
        return personaEntity;
    }

    private PersonaJpa getPersonaJpa(ClienteDto clienteDto, LocalDateTime dateTime) {
        PersonaJpa personaEntity = new PersonaJpa();
        personaEntity.setTipoDocumento(clienteDto.getTipoDocumento());
        personaEntity.setNumeroDocumento(clienteDto.getNumeroDocumento());
        personaEntity.setNombres(clienteDto.getNombres());
        personaEntity.setApellidoPaterno(clienteDto.getApellidoPaterno());
        personaEntity.setApellidoMaterno(clienteDto.getApellidoMaterno());
        personaEntity.setDireccion(clienteDto.getDireccion());
        personaEntity.setCorreoElectronico(clienteDto.getCorreoElectronico());
        personaEntity.setNumeroCelular(clienteDto.getNumeroCelular());
        personaEntity.setFechaCreacion(dateTime);
        personaEntity.setFechaModificacion(dateTime);
        return personaEntity;
    }

    public String generarCodigoPedido() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = LocalDateTime.now().format(dtf);
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return dateTime + "-" + uuid;
    }
}
