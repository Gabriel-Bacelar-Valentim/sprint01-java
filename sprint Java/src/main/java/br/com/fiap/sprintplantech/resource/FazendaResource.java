package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.entity.Fazenda;
import br.com.fiap.sprintplantech.repository.FazendaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fazendas")
public class FazendaResource {
    @Autowired
    private FazendaRepository repo;

    @GetMapping
    public List<Fazenda> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Fazenda findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Fazenda save(@RequestBody Fazenda fazenda) {
        fazenda.setId(null);
        return repo.save(fazenda);
    }

}