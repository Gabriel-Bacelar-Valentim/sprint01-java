package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.SafraRequest;
import br.com.fiap.sprintplantech.dto.response.SafraResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.entity.Safra;

import br.com.fiap.sprintplantech.service.SafraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/safras")
public class SafraResource implements ResourceDTO<SafraRequest, SafraResponse>{

    @Autowired
    private SafraService service;


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SafraResponse> findById(@PathVariable Long id) {
        var entity  = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SafraResponse> save(@RequestBody @Valid SafraRequest r) {
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
    public ResponseEntity<Collection<SafraResponse>> findAll(
            @RequestParam(name = "safra.id", required = false) Long id,
            @RequestParam(name = "safra.cultura", required = false) String cultura,
            @RequestParam(name = "safra.ano", required = false) Long ano,
            @RequestParam(name = "safra.quantidade_produzida", required = false) Double quantidade_produzida,
            @RequestParam(name = "fazenda.fazenda", required = false)Fazenda fazenda
            ) {
        var safra = Safra.builder()
                .id( id )
                .cultura( cultura )
                .ano( ano )
                .quantidade_produzida( quantidade_produzida )
                .fazenda( fazenda )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Safra> example = Example.of(safra, matcher);
        Collection<Safra> safras = service.findAll(example);

        var response = safras.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}