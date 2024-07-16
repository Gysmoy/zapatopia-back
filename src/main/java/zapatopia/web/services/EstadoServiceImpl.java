package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.EstadoDto;
import zapatopia.web.repository.EstadoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<EstadoDto> listarEstados() {

        List<EstadoDto> estados = new ArrayList<>();

        estadoRepository.findAll().forEach(entity -> {
            EstadoDto estadoDto = new EstadoDto();
            estadoDto.setId(entity.getId());
            estadoDto.setEstado(entity.getEstado());
            estadoDto.setDescripcion(entity.getDescripcion());
            estados.add(estadoDto);
        });

        return estados;
    }
}
