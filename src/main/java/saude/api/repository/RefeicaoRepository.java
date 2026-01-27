package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saude.api.model.Refeicao;

/**
 * Repository para a entidade Refeicao.
 * 
 * Ao estender JpaRepository, esta interface herda automaticamente métodos prontos para:
 * - save(): Salvar/atualizar uma refeição
 * - findById(): Buscar refeição por ID
 * - findAll(): Listar todas as refeições
 * - deleteById(): Deletar refeição por ID
 * - count(): Contar total de refeições
 * - existsById(): Verificar se refeição existe
 * 
 * JpaRepository<Refeicao, Long>:
 * - Refeicao: Tipo da entidade que será gerenciada
 * - Long: Tipo do ID da entidade
 * 
 * Não é necessário implementar nada! O Spring Data JPA cria a implementação automaticamente.
 */
@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    // Métodos personalizados podem ser adicionados aqui, por exemplo:
    // List<Refeicao> findByTipo(String tipo);
    // List<Refeicao> findByData(LocalDate data);
}
