package br.com.uel.resenhapromax.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "resenhas")
@Getter
@Setter
@NoArgsConstructor
public class Resenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lugar é obrigatório.")
    @Size(max = 100, message = "Lugar deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String lugar;

    // opcional
    private String endereco;

    // formato que vem do input datetime-local
    @NotNull(message = "Data e horário são obrigatórios.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime dataHora;

    // nomes separados por virgula
    @NotBlank(message = "Informe quem estava presente.")
    @Column(nullable = false)
    private String presentes;

    public Resenha(String lugar, String endereco, LocalDateTime dataHora, String presentes) {
        this.lugar = lugar;
        this.endereco = endereco;
        this.dataHora = dataHora;
        this.presentes = presentes;
    }
}
