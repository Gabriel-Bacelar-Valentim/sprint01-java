package br.com.fiap.sprintplantech.service;

import br.com.fiap.sprintplantech.dto.request.FazendaRequest;
import br.com.fiap.sprintplantech.dto.response.FazendaResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.repository.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class FazendaService implements ServiceDTO<Fazenda, FazendaRequest, FazendaResponse> {

    @Autowired
    private FazendaRepository repo;

    @Autowired
    private AgricultorService agricultorService;

    @Override
    public Collection<Fazenda> findAll(Example<Fazenda> example) {
        return repo.findAll( example );
    }

    @Override
    public Fazenda findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Fazenda save(Fazenda e) {
        return repo.save( e );
    }

    @Override
    public Fazenda toEntity(FazendaRequest dto) {
        if (Objects.isNull( dto )) return null;

        var agricultor = this.findById(dto.agricultor().id());

        return Fazenda.builder()
                .nome( dto.nome() )
                .tamanho_hectares( dto.tamanho_hectares() )
                .localizacao( dto.localizacao() )
                .agricultor(agricultor.getAgricultor())
                .build();
    }

    @Override
    public FazendaResponse toResponse(Fazenda e) {
        return FazendaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .tamanho_hectares(e.getTamanho_hectares())
                .localizacao(e.getLocalizacao())
                .agricultor(agricultorService.toResponse(Agricultor.builder().build()))
                .build();
    }
}
