package br.com.uel.resenhapromax.controller;

import br.com.uel.resenhapromax.service.ResenhaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resenhas")
public class ResenhaController {
    private final ResenhaService resenhaService;

    public ResenhaController(ResenhaService resenhaService) {
        this.resenhaService = resenhaService;
    }

    // manda a lista pra view, com pesquisa e ordenacao opcionais
    @GetMapping
    public String listarResenhas(@RequestParam(required = false) String nome,
                                 @RequestParam(defaultValue = "dataHora") String campo,
                                 @RequestParam(defaultValue = "asc") String direcao,
                                 Model model) {
        if (nome != null && !nome.isBlank()) {
            model.addAttribute("resenhas", resenhaService.pesquisarResenhas(nome, campo, direcao));
        } else {
            model.addAttribute("resenhas", resenhaService.listarResenhas(campo, direcao));
        }
        model.addAttribute("nome", nome);
        model.addAttribute("campo", campo);
        model.addAttribute("direcao", direcao);
        return "resenhas/listar_resenhas";
    }
}
