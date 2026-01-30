package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Sono;
import saude.api.repository.SonoRepository;

import java.util.List;

/**
 * Controller MVC para gerenciar CRUD de Sono via páginas HTML.
 * 
 * @Controller: Retorna páginas HTML (diferente de @RestController que retorna JSON)
 * @RequestMapping: Define a rota base /sono-crud
 */
@Controller
@RequestMapping("/sono-crud")
public class SonoCrudController {

    @Autowired
    private SonoRepository sonoRepository;

    /**
     * Exibe a página CRUD com lista de registros de sono.
     * GET http://localhost:8080/sono-crud
     */
    @GetMapping
    public String exibirCrud(Model model) {
        List<Sono> registros = sonoRepository.findAll();
        model.addAttribute("registros", registros);
        model.addAttribute("sono", new Sono());
        return "sono-crud";
    }

    /**
     * Salva um novo registro de sono via formulário HTML.
     * POST http://localhost:8080/sono-crud/salvar
     */
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Sono sono) {
        sonoRepository.save(sono);
        return "redirect:/sono-crud";
    }

    /**
     * Atualiza um registro de sono existente via formulário HTML.
     * POST http://localhost:8080/sono-crud/editar
     */
    @PostMapping("/editar")
    public String editar(@ModelAttribute Sono sono) {
        sonoRepository.save(sono);
        return "redirect:/sono-crud";
    }

    /**
     * Exclui um registro de sono por ID.
     * GET http://localhost:8080/sono-crud/excluir/1
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        sonoRepository.deleteById(id);
        return "redirect:/sono-crud";
    }
}
