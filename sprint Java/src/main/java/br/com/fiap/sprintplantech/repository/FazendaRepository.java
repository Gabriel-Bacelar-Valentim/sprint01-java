package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, Long> {
}
