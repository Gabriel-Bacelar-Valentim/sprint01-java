package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Safras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FazendasRepository extends JpaRepository<Safras, Long> {
}
