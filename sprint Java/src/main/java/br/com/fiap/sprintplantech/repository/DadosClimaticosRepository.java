package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Agricultores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosClimaticosRepository extends JpaRepository<Agricultores, Long> {
}