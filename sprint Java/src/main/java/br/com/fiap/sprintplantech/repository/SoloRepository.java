package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.DadosClimaticos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoloRepository extends JpaRepository<DadosClimaticos, Long> {
}