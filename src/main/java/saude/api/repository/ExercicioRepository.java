package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saude.api.model.Exercicio;

/**
 * Repository para a entidade Exercicio.
 * 
 * @Query: Permite criar consultas personalizadas usando JPQL ou SQL nativo
 * AVG(): Função SQL para calcular média
 */
@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    
    /**
     * Calcula a média de tempo gasto em exercícios.
     * @return Média de tempo em minutos (ou 0.0 se não houver registros)
     */
    @Query("SELECT COALESCE(AVG(e.tempo), 0.0) FROM Exercicio e")
    Double calcularMediaTempo();
    
    /**
     * Calcula a média de carga utilizada nos exercícios.
     * @return Média de carga em kg (ou 0.0 se não houver registros)
     */
    @Query("SELECT COALESCE(AVG(e.carga), 0.0) FROM Exercicio e")
    Double calcularMediaCarga();
}
