package Service;

import Model.Resenha;
import Repository.resenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class resenhaService {
    @Autowired
    private resenhaRepository resenhaRepository;

    public List<Resenha> listarResenhas(){
        return resenhaRepository.findAll();
    }

    public Resenha cadastrarResenha(Resenha resenha){
        return resenhaRepository.save(resenha);
    }

    public Resenha atualizarResenha(Long id, Resenha resenha) {
        Resenha resenhaAtualizar = resenhaRepository.findById(id).orElse(null);

        if (resenhaAtualizar != null) {
            resenhaAtualizar.setNome(resenha.getNome());
            resenhaAtualizar.setDescricao(resenha.getDescricao());
            resenhaAtualizar.setCategoria(resenha.getCategoria());
            return resenhaRepository.save(resenhaAtualizar);
        } else {
            throw new RuntimeException("Resenha não cadastrada | id " + id);
        }
    }

    public void excluirResenha(Long id){
        if (!resenhaRepository.existsById(id)) {
            throw new RuntimeException("Resenha não encontrada | id " + id);
        }
        resenhaRepository.deleteById(id);
    }
}
