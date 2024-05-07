package br.com.fiap.sprintplantech.resource;

import br.com.fiap.sprintplantech.entity.DadoClimatico;
import br.com.fiap.sprintplantech.repository.DadoClimaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dadosclimaticos")
public class DadoClimaticoResource {
    @Autowired
    private DadoClimaticoRepository repo;


    @GetMapping
    public List<DadoClimatico> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public DadoClimatico findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public DadoClimatico save(@RequestBody DadoClimatico dadoClimatico) {
        dadoClimatico.setId(null);
        return repo.save(dadoClimatico);
    }

}