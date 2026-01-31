package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saude.api.model.Sono;

/**
 * Repository para a entidade Sono.
 */
@Repository
public interface SonoRepository extends JpaRepository<Sono, Long> {
    
    /**
     * Calcula a média de horas dormidas.
     * @return Média de horas dormidas (ou 0.0 se não houver registros)
     */
    @Query("SELECT COALESCE(AVG(s.horasDormidas), 0.0) FROM Sono s")
    Double calcularMediaHorasDormidas();
}
