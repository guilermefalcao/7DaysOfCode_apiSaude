package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Refeicao;
import saude.api.repository.RefeicaoRepository;

import java.util.List;

/**
 * Controller MVC para gerenciar CRUD de Refeições via páginas HTML.
 * 
 * @Controller: Retorna páginas HTML (diferente de @RestController que retorna JSON)
 * @RequestMapping: Define a rota base /refeicao-crud
 */
@Controller
@RequestMapping("/refeicao-crud")
public class RefeicaoCrudController {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    /**
     * Exibe a página CRUD com lista de refeições.
     * GET http://localhost:8080/refeicao-crud
     */
    @GetMapping
    public String exibirCrud(Model model) {
        List<Refeicao> refeicoes = refeicaoRepository.findAll();
        model.addAttribute("refeicoes", refeicoes);
        model.addAttribute("refeicao", new Refeicao());
        return "refeicao-crud";
    }

    /**
     * Salva uma nova refeição via formulário HTML.
     * POST http://localhost:8080/refeicao-crud/salvar
     */
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Refeicao refeicao) {
        refeicaoRepository.save(refeicao);
        return "redirect:/refeicao-crud";
    }

    /**
     * Atualiza uma refeição existente via formulário HTML.
     * POST http://localhost:8080/refeicao-crud/editar
     */
    @PostMapping("/editar")
    public String editar(@ModelAttribute Refeicao refeicao) {
        refeicaoRepository.save(refeicao);
        return "redirect:/refeicao-crud";
    }

    /**
     * Exclui uma refeição por ID.
     * GET http://localhost:8080/refeicao-crud/excluir/1
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        refeicaoRepository.deleteById(id);
        return "redirect:/refeicao-crud";
    }
}
