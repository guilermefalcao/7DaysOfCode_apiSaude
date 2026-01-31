package saude.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saude.api.repository.ExercicioRepository;
import saude.api.repository.RefeicaoRepository;
import saude.api.repository.SonoRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Service para gerar relatórios com estatísticas de saúde.
 * 
 * @Service: Marca a classe como componente de serviço (lógica de negócio)
 */
@Service
public class RelatorioService {

    @Autowired
    private ExercicioRepository exercicioRepository;
    
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    
    @Autowired
    private SonoRepository sonoRepository;

    /**
     * Gera relatório completo com todas as médias.
     */
    public Map<String, Double> gerarRelatorio() {
        Map<String, Double> relatorio = new HashMap<>();
        relatorio.put("mediaTempo", exercicioRepository.calcularMediaTempo());
        relatorio.put("mediaCarga", exercicioRepository.calcularMediaCarga());
        relatorio.put("mediaQuantidade", refeicaoRepository.calcularMediaQuantidade());
        relatorio.put("mediaHorasDormidas", sonoRepository.calcularMediaHorasDormidas());
        return relatorio;
    }
}
