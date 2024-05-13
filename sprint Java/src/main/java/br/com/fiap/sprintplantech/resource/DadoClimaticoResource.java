package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.DadoClimaticoRequest;
import br.com.fiap.sprintplantech.dto.response.DadoClimaticoResponse;
import br.com.fiap.sprintplantech.entity.DadoClimatico;
import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.service.DadoClimaticoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/dadosclimaticos")
public class DadoClimaticoResource implements ResourceDTO<DadoClimaticoRequest, DadoClimaticoResponse>{
    @Autowired
    private DadoClimaticoService service;


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<DadoClimaticoResponse> findById(@PathVariable Long id) {
        var entity = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse( entity );
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<DadoClimaticoResponse> save(@RequestBody @Valid DadoClimaticoRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

                var response = service.toResponse( saved );
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Collection<DadoClimaticoResponse>> findAll(
            @RequestParam(name = "dadoclimatico.id", required = false) Long id,
            @RequestParam(name = "dadoclimatico.data", required = false)Date data,
            @RequestParam(name = "dadoclimatico.temperatura_media", required = false)Double temperatura_media,
            @RequestParam(name = "dadoclimatico.umidade_relativa", required = false) Double umidade_relativa,
            @RequestParam(name = "dadoclimatico.precipitacao", required = false) Long precipitacao,
            @RequestParam(name = "fazenda.fazenda", required = false)Fazenda fazenda


            ){
        var ddClimatico = DadoClimatico.builder()
                .id( id )
                .data( data )
                .temperatura_media( temperatura_media )
                .umidade_relativa( umidade_relativa )
                .precipitacao( precipitacao )
                .fazenda( fazenda )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<DadoClimatico> example = Example.of(ddClimatico, matcher);
        Collection<DadoClimatico> dadoClimaticos = service.findAll(example);

        var response = dadoClimaticos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }



}