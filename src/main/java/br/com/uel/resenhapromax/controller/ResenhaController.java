package br.com.uel.resenhapromax.Controller;

import br.com.uel.resenhapromax.Model.Resenha;
import br.com.uel.resenhapromax.Service.resenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resenhas")
public class resenhaController {
    private final resenhaService resenhaService;

    @Autowired
    public resenhaController(resenhaService resenhaService) {
        this.resenhaService = resenhaService;
    }

    @GetMapping
    public String listarResenhas(Resenha resenha) {
        resenha.addAttribute("resenhas", resenhaService.listarResenhas());
        return "resenhas/listar_resenhas"; // ta com problema esse addAttribute
    }


}
