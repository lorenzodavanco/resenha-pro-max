package br.com.uel.resenhapromax.Service;

import br.com.uel.resenhapromax.Model.Resenha;
import br.com.uel.resenhapromax.Repository.resenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class resenhaService {
    private final resenhaRepository resenhaRepository;

    @Autowired
    public resenhaService(resenhaRepository resenhaRepository) {
        this.resenhaRepository = resenhaRepository;
    }

    public List<Resenha> listarResenhas(){
        return resenhaRepository.findAll();
    }

    public Resenha buscarResenha(Long id){
        return resenhaRepository.findById(id).orElse(null);
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
