package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class resenhaModel {
    public String nome;
    public String descricao;
    public String categoria;

    public resenhaModel(String nome, String descricao, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
