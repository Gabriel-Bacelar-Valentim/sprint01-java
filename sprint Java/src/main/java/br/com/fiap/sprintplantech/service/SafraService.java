package br.com.fiap.sprintplantech.service;

import br.com.fiap.sprintplantech.dto.request.SafraRequest;
import br.com.fiap.sprintplantech.dto.response.SafraResponse;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.entity.Safra;
import br.com.fiap.sprintplantech.repository.SafraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class SafraService implements ServiceDTO<Safra, SafraRequest, SafraResponse>{

    @Autowired
    private SafraRepository repo;

    @Autowired
    private FazendaService fazendaService;

    @Override
    public Collection<Safra> findAll(Example<Safra> example) {
        return repo.findAll( example );
    }

    @Override
    public Safra findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Safra save(Safra e) {
        return repo.save( e );
    }

    @Override
    public Safra toEntity(SafraRequest dto) {
        if (Objects.isNull( dto )) return null;

        var fazenda = this.findById(dto.fazenda().id());

        return Safra.builder()
                .cultura( dto.cultura() )
                .ano( dto.ano() )
                .quantidade_produzida( dto.quantidade_produzida() )
                .fazenda(fazenda.getFazenda())
                .build();
    }

    @Override
    public SafraResponse toResponse(Safra e) {
        return SafraResponse.builder()
                .id(e.getId())
                .cultura(e.getCultura())
                .ano(e.getAno())
                .quantidade_produzida(e.getQuantidade_produzida())
                .fazenda(fazendaService.toResponse(Fazenda.builder().build()))
                .build();
    }
}
