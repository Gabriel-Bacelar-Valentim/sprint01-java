package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.DadoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DadoClimaticoRepository extends JpaRepository<DadoClimatico, Long> {
    List<DadoClimatico> findByFazendaId(Long id);
}