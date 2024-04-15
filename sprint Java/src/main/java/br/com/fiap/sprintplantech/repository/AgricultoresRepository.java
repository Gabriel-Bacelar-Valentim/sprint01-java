package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Fazendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultoresRepository extends JpaRepository<Fazendas, Long> {
}