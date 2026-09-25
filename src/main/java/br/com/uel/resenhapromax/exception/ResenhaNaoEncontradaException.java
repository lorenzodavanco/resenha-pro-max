package br.com.uel.resenhapromax.exception;

// erro quando o id nao existe no banco
public class ResenhaNaoEncontradaException extends RuntimeException {
    public ResenhaNaoEncontradaException(Long id) {
        super("Resenha não encontrada | id " + id);
    }
}
