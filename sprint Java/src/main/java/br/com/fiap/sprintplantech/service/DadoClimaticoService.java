package br.com.fiap.sprintplantech.service;

import br.com.fiap.sprintplantech.dto.request.DadoClimaticoRequest;
import br.com.fiap.sprintplantech.dto.response.DadoClimaticoResponse;
import br.com.fiap.sprintplantech.entity.DadoClimatico;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.repository.DadoClimaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class DadoClimaticoService implements ServiceDTO<DadoClimatico, DadoClimaticoRequest, DadoClimaticoResponse>{

    @Autowired
    private DadoClimaticoRepository repo;

    @Autowired
    private FazendaService fazendaService;

    @Override
    public Collection<DadoClimatico> findAll(Example<DadoClimatico> example) {
        return repo.findAll( example );
    }

    @Override
    public DadoClimatico findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public DadoClimatico save(DadoClimatico e) {
        return repo.save( e );
    }

    @Override
    public DadoClimatico toEntity(DadoClimaticoRequest dto) {

        if (Objects.isNull( dto )) return null;

        var fazenda = this.findById(dto.fazenda().id());

        return DadoClimatico.builder()
                .data( dto.data() )
                .temperatura_media( dto.temperatura_media() )
                .umidade_relativa( dto.umidade_relativa() )
                .precipitacao( dto.precipitacao() )
                .fazenda( fazenda.getFazenda() )
                .build();
    }

    @Override
    public DadoClimaticoResponse toResponse(DadoClimatico e) {
        return DadoClimaticoResponse.builder()
                .id( e.getId() )
                .data( e.getData() )
                .temperatura_media( e.getTemperatura_media() )
                .umidade_relativa( e.getUmidade_relativa() )
                .precipitacao( e.getPrecipitacao() )
                .fazenda( fazendaService.toResponse(Fazenda.builder().build()))
                .build();
    }
}
