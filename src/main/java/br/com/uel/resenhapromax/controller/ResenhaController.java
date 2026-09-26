package br.com.uel.resenhapromax.controller;

import br.com.uel.resenhapromax.model.Resenha;
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
    public String listarResenhas(@RequestParam(defaultValue = "") String lugar,
                                 @RequestParam(defaultValue = "") String pessoa,
                                 @RequestParam(defaultValue = "dataHora") String campo,
                                 @RequestParam(defaultValue = "desc") String direcao,
                                 Model model) {
        carregarLista(model, lugar, pessoa, campo, direcao);
        return "resenhas/listar_resenhas";
    }

    // mesma tela, mas com o modal vazio aberto
    @GetMapping("/nova")
    public String novaResenha(Model model) {
        carregarLista(model, "", "", "dataHora", "desc");
        model.addAttribute("resenha", new Resenha());
        return "resenhas/listar_resenhas";
    }

    private void carregarLista(Model model, String lugar, String pessoa, String campo, String direcao) {
        model.addAttribute("resenhas", resenhaService.listarResenhas(lugar, pessoa, campo, direcao));
        model.addAttribute("lugar", lugar);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("campo", campo);
        model.addAttribute("direcao", direcao);
    }
}
