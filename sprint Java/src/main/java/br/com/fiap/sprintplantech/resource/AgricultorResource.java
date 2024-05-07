package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.dto.request.AgricultorRequest;
import br.com.fiap.sprintplantech.dto.response.AgricultorResponse;
import br.com.fiap.sprintplantech.entity.Agricultor;
import br.com.fiap.sprintplantech.repository.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agricultores")
public class AgricultorResource implements ResourceDTO<AgricultorRequest, AgricultorResponse>{

    @Autowired
    private AgricultorRepository repo;

    @GetMapping
    public List<Agricultor> findAll(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Agricultor findById(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Agricultor save(@RequestBody Agricultor agricultor) {
        agricultor.setId(null);
        return repo.save(agricultor);
    }
}