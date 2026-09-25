package br.com.uel.resenhapromax.repository;

import br.com.uel.resenhapromax.model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenhaRepository extends JpaRepository<Resenha, Long> {
}
