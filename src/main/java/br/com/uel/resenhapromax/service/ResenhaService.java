package br.com.uel.resenhapromax.service;

import br.com.uel.resenhapromax.model.Resenha;
import br.com.uel.resenhapromax.repository.ResenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenhaService {
    private final ResenhaRepository resenhaRepository;

    @Autowired
    public ResenhaService(ResenhaRepository resenhaRepository) {
        this.resenhaRepository = resenhaRepository;
    }

    public List<Resenha> listarResenhas() {
        return resenhaRepository.findAll();
    }

    public Resenha buscarResenha(Long id) {
        return resenhaRepository.findById(id).orElse(null);
    }

    public Resenha cadastrarResenha(Resenha resenha) {
        return resenhaRepository.save(resenha);
    }

    // copia os dados novos pra resenha que ja existe
    public Resenha atualizarResenha(Long id, Resenha resenha) {
        Resenha resenhaAtualizar = resenhaRepository.findById(id).orElse(null);

        if (resenhaAtualizar != null) {
            resenhaAtualizar.setNome(resenha.getNome());
            resenhaAtualizar.setDescricao(resenha.getDescricao());
            resenhaAtualizar.setCategoria(resenha.getCategoria());
            resenhaAtualizar.setNota(resenha.getNota());
            return resenhaRepository.save(resenhaAtualizar);
        } else {
            throw new RuntimeException("Resenha não cadastrada | id " + id);
        }
    }

    public void excluirResenha(Long id) {
        if (!resenhaRepository.existsById(id)) {
            throw new RuntimeException("Resenha não encontrada | id " + id);
        }
        resenhaRepository.deleteById(id);
    }
}
