package br.com.uel.resenhapromax.Repository;
import br.com.uel.resenhapromax.Model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface resenhaRepository
    extends JpaRepository<Resenha,Long> {
}
