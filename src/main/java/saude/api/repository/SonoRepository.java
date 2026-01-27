package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saude.api.model.Sono;

/**
 * Repository para a entidade Sono.
 * 
 * Ao estender JpaRepository, esta interface herda automaticamente métodos prontos para:
 * - save(): Salvar/atualizar um registro de sono
 * - findById(): Buscar registro de sono por ID
 * - findAll(): Listar todos os registros de sono
 * - deleteById(): Deletar registro de sono por ID
 * - count(): Contar total de registros de sono
 * - existsById(): Verificar se registro de sono existe
 * 
 * JpaRepository<Sono, Long>:
 * - Sono: Tipo da entidade que será gerenciada
 * - Long: Tipo do ID da entidade
 * 
 * Não é necessário implementar nada! O Spring Data JPA cria a implementação automaticamente.
 */
@Repository
public interface SonoRepository extends JpaRepository<Sono, Long> {
    // Métodos personalizados podem ser adicionados aqui, por exemplo:
    // List<Sono> findByQualidade(String qualidade);
    // List<Sono> findByData(LocalDate data);
}
