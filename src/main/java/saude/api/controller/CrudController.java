package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Exercicio;
import saude.api.repository.ExercicioRepository;

import java.util.List;

/**
 * Controller MVC para gerenciar CRUD de Exercícios via páginas HTML.
 * 
 * @Controller: Retorna páginas HTML (diferente de @RestController que retorna JSON)
 * @RequestMapping: Define a rota base /crud
 */
@Controller
@RequestMapping("/crud")
public class CrudController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    /**
     * Exibe a página CRUD com lista de exercícios.
     * 
     * GET http://localhost:8080/crud
     * 
     * @param model Objeto para passar dados para a view (HTML)
     * @return Nome do template (crud.html)
     */
    @GetMapping
    public String exibirCrud(Model model) {
        // Busca todos os exercícios do banco
        List<Exercicio> exercicios = exercicioRepository.findAll();
        
        // Adiciona a lista ao model para ser acessada no HTML
        model.addAttribute("exercicios", exercicios);
        
        // Cria um objeto vazio para o formulário de adição
        model.addAttribute("exercicio", new Exercicio());
        
        return "crud"; // Retorna templates/crud.html
    }

    /**
     * Salva um novo exercício via formulário HTML.
     * 
     * POST http://localhost:8080/crud/salvar
     * 
     * @param exercicio Objeto preenchido automaticamente com dados do formulário
     * @return Redireciona para a página CRUD
     */
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Exercicio exercicio) {
        // Salva o exercício no banco
        exercicioRepository.save(exercicio);
        
        // Redireciona para a página CRUD (atualiza a lista)
        return "redirect:/crud";
    }

    /**
     * Atualiza um exercício existente via formulário HTML.
     * 
     * POST http://localhost:8080/crud/editar
     * 
     * @param exercicio Objeto com dados atualizados do formulário
     * @return Redireciona para a página CRUD
     */
    @PostMapping("/editar")
    public String editar(@ModelAttribute Exercicio exercicio) {
        // Salva (atualiza) o exercício no banco
        exercicioRepository.save(exercicio);
        
        // Redireciona para a página CRUD
        return "redirect:/crud";
    }

    /**
     * Exclui um exercício por ID.
     * 
     * GET http://localhost:8080/crud/excluir/1
     * 
     * @param id ID do exercício a ser excluído
     * @return Redireciona para a página CRUD
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        // Deleta o exercício do banco
        exercicioRepository.deleteById(id);
        
        // Redireciona para a página CRUD
        return "redirect:/crud";
    }
}
