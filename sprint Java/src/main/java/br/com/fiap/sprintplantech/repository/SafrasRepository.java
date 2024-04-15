package br.com.fiap.sprintplantech.repository;

import br.com.fiap.sprintplantech.entity.Solo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafrasRepository extends JpaRepository<Solo, Long> {
}