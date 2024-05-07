package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Safra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafraRepository extends JpaRepository<Safra, Long> {
    List<Safra> findByFazendaId(Long id);
}