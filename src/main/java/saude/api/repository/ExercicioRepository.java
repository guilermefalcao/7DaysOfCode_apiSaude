package saude.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saude.api.model.Exercicio;

/**
 * Repository para a entidade Exercicio.
 * 
 * Ao estender JpaRepository, esta interface herda automaticamente métodos prontos para:
 * - save(): Salvar/atualizar um exercício
 * - findById(): Buscar exercício por ID
 * - findAll(): Listar todos os exercícios
 * - deleteById(): Deletar exercício por ID
 * - count(): Contar total de exercícios
 * - existsById(): Verificar se exercício existe
 * 
 * JpaRepository<Exercicio, Long>:
 * - Exercicio: Tipo da entidade que será gerenciada
 * - Long: Tipo do ID da entidade
 * 
 * Não é necessário implementar nada! O Spring Data JPA cria a implementação automaticamente.
 */
@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    // Métodos personalizados podem ser adicionados aqui, por exemplo:
    // List<Exercicio> findByNome(String nome);
    // List<Exercicio> findByData(LocalDate data);
}
