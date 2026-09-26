package br.com.uel.resenhapromax.service;

import br.com.uel.resenhapromax.exception.ResenhaNaoEncontradaException;
import br.com.uel.resenhapromax.model.Resenha;
import br.com.uel.resenhapromax.repository.ResenhaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenhaService {
    private final ResenhaRepository resenhaRepository;

    public ResenhaService(ResenhaRepository resenhaRepository) {
        this.resenhaRepository = resenhaRepository;
    }

    // lista tudo ordenado pelo campo escolhido (asc ou desc)
    public List<Resenha> listarResenhas(String campo, String direcao) {
        return resenhaRepository.findAll(criarOrdenacao(campo, direcao));
    }

    // busca pelo lugar, mantendo a ordenacao
    public List<Resenha> pesquisarResenhas(String nome, String campo, String direcao) {
        return resenhaRepository.findByLugarContainingIgnoreCase(nome, criarOrdenacao(campo, direcao));
    }

    public Resenha buscarResenha(Long id) {
        return resenhaRepository.findById(id)
                .orElseThrow(() -> new ResenhaNaoEncontradaException(id));
    }

    public Resenha cadastrarResenha(Resenha resenha) {
        return resenhaRepository.save(resenha);
    }

    // copia os dados novos pra resenha que ja existe
    public Resenha atualizarResenha(Long id, Resenha resenha) {
        Resenha resenhaAtualizar = buscarResenha(id);
        resenhaAtualizar.setLugar(resenha.getLugar());
        resenhaAtualizar.setEndereco(resenha.getEndereco());
        resenhaAtualizar.setDataHora(resenha.getDataHora());
        resenhaAtualizar.setPresentes(resenha.getPresentes());
        return resenhaRepository.save(resenhaAtualizar);
    }

    public void excluirResenha(Long id) {
        if (!resenhaRepository.existsById(id)) {
            throw new ResenhaNaoEncontradaException(id);
        }
        resenhaRepository.deleteById(id);
    }

    // so deixa ordenar por esses campos, senao usa data
    private Sort criarOrdenacao(String campo, String direcao) {
        if (!List.of("lugar", "dataHora").contains(campo)) {
            campo = "dataHora";
        }
        if ("desc".equalsIgnoreCase(direcao)) {
            return Sort.by(campo).descending();
        }
        return Sort.by(campo).ascending();
    }
}
