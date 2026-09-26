package br.com.uel.resenhapromax.repository;

import br.com.uel.resenhapromax.model.Resenha;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenhaRepository extends JpaRepository<Resenha, Long> {
    // pesquisa por parte do lugar, sem ligar pra maiuscula
    List<Resenha> findByLugarContainingIgnoreCase(String lugar, Sort sort);
}
