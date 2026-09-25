package br.com.uel.resenhapromax.controller;

import br.com.uel.resenhapromax.service.ResenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resenhas")
public class ResenhaController {
    private final ResenhaService resenhaService;

    @Autowired
    public ResenhaController(ResenhaService resenhaService) {
        this.resenhaService = resenhaService;
    }

    // manda a lista pra view
    @GetMapping
    public String listarResenhas(Model model) {
        model.addAttribute("resenhas", resenhaService.listarResenhas());
        return "resenhas/listar_resenhas";
    }
}
