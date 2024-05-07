package br.com.fiap.sprintplantech.service;

import br.com.fiap.sprintplantech.dto.request.SoloRequest;
import br.com.fiap.sprintplantech.dto.response.SoloResponse;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.entity.Solo;
import br.com.fiap.sprintplantech.repository.SoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class SoloService implements ServiceDTO<Solo, SoloRequest, SoloResponse> {

    @Autowired
    private SoloRepository repo;

    @Autowired
    private FazendaService fazendaService;

    @Override
    public Collection<Solo> findAll(Example<Solo> example) {
        return repo.findAll( example );
    }

    @Override
    public Solo findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Solo save(Solo e) {
        return repo.save( e );
    }

    @Override
    public Solo toEntity(SoloRequest dto) {
        if (Objects.isNull( dto )) return null;

        var fazenda = this.findById(dto.fazenda().id());

        return Solo.builder()
                .tipo_solo(dto.tipo_solo())
                .ph(dto.ph())
                .nivel_nitrogenio(dto.nivel_nitrogenio())
                .nivel_potassio(dto.nivel_potassio())
                .fazenda(fazenda.getFazenda())
                .build();
    }

    @Override
    public SoloResponse toResponse(Solo e) {
        return SoloResponse.builder()
                .id(e.getId())
                .tipo_solo(e.getTipo_solo())
                .ph(e.getPh())
                .nivel_nitrogenio(e.getNivel_nitrogenio())
                .nivel_potassio(e.getNivel_potassio())
                .fazenda(fazendaService.toResponse(Fazenda.builder().build()))
                .build();
    }
}
