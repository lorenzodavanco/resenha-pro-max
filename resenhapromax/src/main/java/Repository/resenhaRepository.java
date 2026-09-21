package Repository;
import Model.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface resenhaRepository
    extends JpaRepository<Resenha,Long> {
}
