package br.com.fiap.sprintplantech.service;


import br.com.fiap.sprintplantech.dto.request.AgricultorRequest;
import br.com.fiap.sprintplantech.dto.response.AgricultorResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.repository.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class AgricultorService implements ServiceDTO<Agricultor, AgricultorRequest, AgricultorResponse> {

    @Autowired
    private AgricultorRepository repo;

    @Override
    public Collection<Agricultor> findAll(Example<Agricultor> example) {
        return repo.findAll(example);
    }

    @Override
    public Agricultor findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Agricultor save(Agricultor e) {
        return repo.save( e );
    }

    @Override
    public Agricultor toEntity(AgricultorRequest dto) {
        if (Objects.isNull( dto )) return null;

        return Agricultor.builder()
                .nome(dto.nome())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .telefone(dto.telefone())
                .email(dto.email())
                .build();
    }

    @Override
    public AgricultorResponse toResponse(Agricultor e) {
        if (Objects.isNull( e )) return null;

        return AgricultorResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cidade(e.getCidade())
                .estado(e.getEstado())
                .telefone(e.getTelefone())
                .email(e.getEmail())
                .build();
    }
}
