package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import saude.api.service.RelatorioService;

import java.util.Map;

/**
 * Controller para exibir relatórios e estatísticas.
 * 
 * @Controller: Retorna páginas HTML
 */
@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    /**
     * Exibe página de relatórios com gráficos.
     * GET http://localhost:8080/relatorios
     */
    @GetMapping
    public String exibirRelatorios(Model model) {
        // Gera relatório com todas as médias
        Map<String, Double> relatorio = relatorioService.gerarRelatorio();
        
        // Adiciona dados ao model para serem acessados no HTML
        model.addAttribute("mediaTempo", relatorio.get("mediaTempo"));
        model.addAttribute("mediaCarga", relatorio.get("mediaCarga"));
        model.addAttribute("mediaQuantidade", relatorio.get("mediaQuantidade"));
        model.addAttribute("mediaHorasDormidas", relatorio.get("mediaHorasDormidas"));
        
        return "relatorios";
    }
}
