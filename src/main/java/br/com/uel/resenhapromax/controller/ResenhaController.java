package br.com.uel.resenhapromax.controller;

import br.com.uel.resenhapromax.model.Resenha;
import br.com.uel.resenhapromax.service.ResenhaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // mesma tela, com o modal preenchido pra editar
    @GetMapping("/{id}/editar")
    public String editarResenha(@PathVariable Long id, Model model) {
        carregarLista(model, "", "", "dataHora", "desc");
        model.addAttribute("resenha", resenhaService.buscarResenha(id));
        return "resenhas/listar_resenhas";
    }

    // se tiver erro volta pro modal, se nao salva e volta pra lista
    @PostMapping("/salvar")
    public String salvarResenha(@Valid @ModelAttribute("resenha") Resenha resenha, BindingResult result,
                                Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            carregarLista(model, "", "", "dataHora", "desc");
            return "resenhas/listar_resenhas";
        }
        if (resenha.getId() == null) {
            resenhaService.cadastrarResenha(resenha);
            attrs.addFlashAttribute("sucesso", "Resenha cadastrada com sucesso!");
        } else {
            resenhaService.atualizarResenha(resenha.getId(), resenha);
            attrs.addFlashAttribute("sucesso", "Resenha atualizada com sucesso!");
        }
        return "redirect:/resenhas";
    }

    @PostMapping("/{id}/excluir")
    public String excluirResenha(@PathVariable Long id, RedirectAttributes attrs) {
        resenhaService.excluirResenha(id);
        attrs.addFlashAttribute("sucesso", "Resenha excluída com sucesso!");
        return "redirect:/resenhas";
    }

    private void carregarLista(Model model, String lugar, String pessoa, String campo, String direcao) {
        model.addAttribute("resenhas", resenhaService.listarResenhas(lugar, pessoa, campo, direcao));
        model.addAttribute("lugar", lugar);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("campo", campo);
        model.addAttribute("direcao", direcao);
    }
}
