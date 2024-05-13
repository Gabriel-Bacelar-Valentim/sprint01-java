package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.SoloRequest;
import br.com.fiap.sprintplantech.dto.response.SoloResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.entity.Solo;
import br.com.fiap.sprintplantech.repository.SoloRepository;
import br.com.fiap.sprintplantech.service.SoloService;
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
@RequestMapping("/solo")
public class SoloResource implements ResourceDTO<SoloRequest, SoloResponse>{

    @Autowired
    private SoloService service;
    
    @Override
    public ResponseEntity<SoloResponse> findById(@PathVariable Long id) {
        var entity  = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<SoloResponse> save(@RequestBody @Valid SoloRequest r) {
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
    public ResponseEntity<Collection<SoloResponse>> findAll(
            @RequestParam(name = "solo.id", required = false) Long id,
            @RequestParam(name = "solo.tipo_solo", required = false) String tipo_solo,
            @RequestParam(name = "solo.nivel_nitrogenio", required = false) Double nivel_nitrogenio,
            @RequestParam(name = "solo.nivel_potassio", required = false) Double nivel_potassio,
            @RequestParam(name = "fazenda.fazenda", required = false)Fazenda fazenda
            ) {
        var solo = Solo.builder()
                .id( id )
                .tipo_solo( tipo_solo )
                .nivel_nitrogenio( nivel_nitrogenio )
                .nivel_potassio( nivel_potassio )
                .fazenda( fazenda )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Solo> example = Example.of(solo, matcher);
        Collection<Solo> solos = service.findAll(example);

        var response = solos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}