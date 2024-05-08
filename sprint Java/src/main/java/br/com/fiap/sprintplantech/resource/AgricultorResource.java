package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.AgricultorRequest;
import br.com.fiap.sprintplantech.dto.response.AgricultorResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.service.AgricultorService;
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
@RequestMapping("/agricultores")
public class AgricultorResource implements ResourceDTO<AgricultorRequest, AgricultorResponse>{

    @Autowired
    private AgricultorService service;


    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgricultorResponse> findById(@PathVariable Long id) {
        var entity  = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<AgricultorResponse> save(@RequestBody @Valid AgricultorRequest r) {
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
    public ResponseEntity<Collection<AgricultorResponse>> findAll(

            @RequestParam(name = "agricultor.nome", required = false) String nome,
            @RequestParam(name = "agricultor.cidade", required = false) String cidade,
            @RequestParam(name = "agricultor.estado", required = false) String estado,
            @RequestParam(name = "agricultor.telefone", required = false) String telefone,
            @RequestParam(name = "agricultor.email", required = false) String email

    ) {

        var agricultor = Agricultor.builder()
                .nome(nome)
                .cidade(cidade)
                .estado(estado)
                .telefone(telefone)
                .email(email)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Agricultor> example = Example.of(agricultor, matcher);
        Collection<Agricultor> agricultores = service.findAll(example);

        var response = agricultores.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }


}