package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.FazendaRequest;
import br.com.fiap.sprintplantech.dto.response.FazendaResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.repository.FazendaRepository;
import br.com.fiap.sprintplantech.service.FazendaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/fazendas")
public class FazendaResource implements ResourceDTO<FazendaRequest, FazendaResponse>{
    @Autowired
    private FazendaService service;


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<FazendaResponse> findById(@PathVariable Long id) {
        var entity  = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<FazendaResponse> save(@RequestBody @Valid FazendaRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        var response = service.toResponse(saved);
        return ResponseEntity.created(uri).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Collection<FazendaResponse>> findAll(
            @RequestParam(name = "fazenda.id", required = false) Long id,
            @RequestParam(name = "fazenda.tamanho_hectares", required = false) String nome,
            @RequestParam(name = "fazenda.estado", required = false) Double tamanho_hectares,
            @RequestParam(name = "fazenda.localizacao", required = false) String localizacao,
            @RequestParam(name = "agricultor.agricultor", required = false)Agricultor agricultor

            ){
        var fazenda = Fazenda.builder()
                .id( id )
                .nome( nome )
                .tamanho_hectares( tamanho_hectares )
                .localizacao( localizacao )
                .agricultor( agricultor )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Fazenda> example = Example.of(fazenda, matcher);
        Collection<Fazenda> fazendas = service.findAll(example);

        var response = fazendas.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}