package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saude.api.model.Refeicao;

/**
 * Repository para a entidade Refeicao.
 */
@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    
    /**
     * Calcula a média de quantidade (gramas) das refeições.
     * @return Média de quantidade em gramas (ou 0.0 se não houver registros)
     */
    @Query("SELECT COALESCE(AVG(r.quantidade), 0.0) FROM Refeicao r")
    Double calcularMediaQuantidade();
}
