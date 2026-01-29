package saude.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller para a página inicial (Home).
 * 
 * @Controller: Indica que esta classe é um controller MVC (retorna páginas HTML)
 * Diferente de @RestController que retorna JSON
 */
@Controller
public class HomeController {

    /**
     * Exibe a página inicial.
     * 
     * @GetMapping("/"): Mapeia a rota raiz da aplicação
     * @return Nome do template HTML (index.html)
     * 
     * Acesse: http://localhost:8080/
     */
    @GetMapping("/")
    public String home() {
        return "index"; // Retorna o arquivo templates/index.html
    }
}
