package br.com.uel.resenhapromax.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name="Resenhas")
@Getter
@Setter
public class Resenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 1, max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;

    private String descricao;

    @NotBlank(message = "Categoria é obrigatória.")
    private String categoria;

    public Resenha(String nome, String descricao, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
