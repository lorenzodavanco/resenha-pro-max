package br.com.uel.resenhapromax.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resenhas")
@Getter
@Setter
@NoArgsConstructor
public class Resenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nome;

    // opcional
    private String descricao;

    @NotBlank(message = "Categoria é obrigatória.")
    @Column(nullable = false)
    private String categoria;

    // nota de 0 a 10, usada na ordenacao
    @NotNull(message = "Nota é obrigatória.")
    @Min(value = 0, message = "Nota mínima é 0.")
    @Max(value = 10, message = "Nota máxima é 10.")
    @Column(nullable = false)
    private Integer nota;

    public Resenha(String nome, String descricao, String categoria, Integer nota) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.nota = nota;
    }
}
