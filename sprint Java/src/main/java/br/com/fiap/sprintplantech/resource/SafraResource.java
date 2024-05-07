package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.entity.Safra;
import br.com.fiap.sprintplantech.repository.SafraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/safras")
public class SafraResource {

    @Autowired
    private SafraRepository repo;

    @GetMapping
    public List<Safra> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Safra findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Safra save(@RequestBody Safra safra) {
        safra.setId(null);
        return repo.save(safra);
    }
}