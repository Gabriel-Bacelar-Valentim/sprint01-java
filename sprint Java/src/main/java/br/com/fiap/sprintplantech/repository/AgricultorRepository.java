package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {
}