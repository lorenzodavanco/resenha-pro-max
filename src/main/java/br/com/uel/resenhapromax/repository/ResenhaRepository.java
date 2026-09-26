package br.com.uel.resenhapromax.repository;

import br.com.uel.resenhapromax.model.Resenha;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenhaRepository extends JpaRepository<Resenha, Long> {
    // filtra por lugar e pessoa, texto vazio traz tudo
    List<Resenha> findByLugarContainingIgnoreCaseAndPresentesContainingIgnoreCase(String lugar, String pessoa, Sort sort);
}
