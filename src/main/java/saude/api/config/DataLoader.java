package saude.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import saude.api.model.Exercicio;
import saude.api.model.Refeicao;
import saude.api.model.Sono;
import saude.api.repository.ExercicioRepository;
import saude.api.repository.RefeicaoRepository;
import saude.api.repository.SonoRepository;

import java.time.LocalDate;

/**
 * DataLoader: Popula o banco com dados iniciais ao iniciar a aplicação.
 * 
 * @Component: Marca como componente Spring (gerenciado pelo container)
 * CommandLineRunner: Interface que executa código após a aplicação iniciar
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ExercicioRepository exercicioRepository;
    
    @Autowired
    private RefeicaoRepository refeicaoRepository;
    
    @Autowired
    private SonoRepository sonoRepository;

    /**
     * Método executado automaticamente ao iniciar a aplicação.
     * Popula o banco H2 com dados de exemplo.
     */
    @Override
    public void run(String... args) throws Exception {
        // Limpa dados existentes (caso haja)
        exercicioRepository.deleteAll();
        refeicaoRepository.deleteAll();
        sonoRepository.deleteAll();
        
        // Popula Exercícios
        carregarExercicios();
        
        // Popula Refeições
        carregarRefeicoes();
        
        // Popula Sono
        carregarSono();
        
        System.out.println("✅ Banco de dados populado com dados de exemplo!");
    }
    
    private void carregarExercicios() {
        Exercicio ex1 = new Exercicio();
        ex1.setNome("Supino Reto");
        ex1.setSeries(4);
        ex1.setRepeticoes(12);
        ex1.setCarga(80.0);
        ex1.setTempo(45);
        ex1.setData(LocalDate.now().minusDays(4));
        
        Exercicio ex2 = new Exercicio();
        ex2.setNome("Agachamento");
        ex2.setSeries(4);
        ex2.setRepeticoes(10);
        ex2.setCarga(100.0);
        ex2.setTempo(50);
        ex2.setData(LocalDate.now().minusDays(3));
        
        Exercicio ex3 = new Exercicio();
        ex3.setNome("Levantamento Terra");
        ex3.setSeries(3);
        ex3.setRepeticoes(8);
        ex3.setCarga(120.0);
        ex3.setTempo(40);
        ex3.setData(LocalDate.now().minusDays(2));
        
        Exercicio ex4 = new Exercicio();
        ex4.setNome("Rosca Direta");
        ex4.setSeries(3);
        ex4.setRepeticoes(15);
        ex4.setCarga(20.0);
        ex4.setTempo(30);
        ex4.setData(LocalDate.now().minusDays(1));
        
        Exercicio ex5 = new Exercicio();
        ex5.setNome("Corrida");
        ex5.setSeries(1);
        ex5.setRepeticoes(1);
        ex5.setCarga(0.0);
        ex5.setTempo(30);
        ex5.setData(LocalDate.now());
        
        exercicioRepository.save(ex1);
        exercicioRepository.save(ex2);
        exercicioRepository.save(ex3);
        exercicioRepository.save(ex4);
        exercicioRepository.save(ex5);
    }
    
    private void carregarRefeicoes() {
        Refeicao ref1 = new Refeicao();
        ref1.setNome("Frango com batata doce");
        ref1.setTipo("Almoço");
        ref1.setQuantidade(400.0);
        ref1.setData(LocalDate.now().minusDays(4));
        
        Refeicao ref2 = new Refeicao();
        ref2.setNome("Omelete com aveia");
        ref2.setTipo("Café da manhã");
        ref2.setQuantidade(250.0);
        ref2.setData(LocalDate.now().minusDays(3));
        
        Refeicao ref3 = new Refeicao();
        ref3.setNome("Salmão com arroz integral");
        ref3.setTipo("Jantar");
        ref3.setQuantidade(450.0);
        ref3.setData(LocalDate.now().minusDays(2));
        
        Refeicao ref4 = new Refeicao();
        ref4.setNome("Salada de frutas");
        ref4.setTipo("Lanche");
        ref4.setQuantidade(200.0);
        ref4.setData(LocalDate.now().minusDays(1));
        
        Refeicao ref5 = new Refeicao();
        ref5.setNome("Peito de peru com pão integral");
        ref5.setTipo("Café da manhã");
        ref5.setQuantidade(300.0);
        ref5.setData(LocalDate.now());
        
        refeicaoRepository.save(ref1);
        refeicaoRepository.save(ref2);
        refeicaoRepository.save(ref3);
        refeicaoRepository.save(ref4);
        refeicaoRepository.save(ref5);
    }
    
    private void carregarSono() {
        Sono sono1 = new Sono();
        sono1.setHorasDormidas(8.0);
        sono1.setQualidade("Boa");
        sono1.setData(LocalDate.now().minusDays(4));
        
        Sono sono2 = new Sono();
        sono2.setHorasDormidas(7.5);
        sono2.setQualidade("Boa");
        sono2.setData(LocalDate.now().minusDays(3));
        
        Sono sono3 = new Sono();
        sono3.setHorasDormidas(6.0);
        sono3.setQualidade("Moderada");
        sono3.setData(LocalDate.now().minusDays(2));
        
        Sono sono4 = new Sono();
        sono4.setHorasDormidas(9.0);
        sono4.setQualidade("Excelente");
        sono4.setData(LocalDate.now().minusDays(1));
        
        Sono sono5 = new Sono();
        sono5.setHorasDormidas(7.0);
        sono5.setQualidade("Boa");
        sono5.setData(LocalDate.now());
        
        sonoRepository.save(sono1);
        sonoRepository.save(sono2);
        sonoRepository.save(sono3);
        sonoRepository.save(sono4);
        sonoRepository.save(sono5);
    }
}
