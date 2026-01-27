# Resumo das Aulas - 7DaysOfCode API Saúde

## 📚 Aula 1: Configuração Inicial do Projeto

### O que foi feito:
1. **Criação do projeto no Spring Initializr**
   - Configurado com Java 17 e Spring Boot 3.4.1
   - Adicionadas as dependências necessárias

2. **Dependências adicionadas:**
   - **Spring Web**: Para criar APIs REST
   - **Spring Data JPA**: Para trabalhar com banco de dados
   - **H2 Database**: Banco de dados em memória para desenvolvimento
   - **Lombok**: Reduz código boilerplate (getters, setters, etc)
   - **Spring Boot DevTools**: Reinicia automaticamente ao salvar arquivos
   - **Thymeleaf**: Template engine (para futuras páginas web)
   - **Spring Boot Actuator**: Monitoramento da aplicação
   - **Maven**: Gerenciador de dependências

3. **Configuração do application.properties:**
   - Configurado banco H2 em memória
   - Habilitado H2 Console para visualizar o banco
   - Configurado JPA para criar tabelas automaticamente

### Conceitos aprendidos:
- **Spring Boot**: Framework que facilita criar aplicações Java
- **Maven**: Gerencia dependências e build do projeto
- **application.properties**: Arquivo de configuração da aplicação

---

## 📚 Aula 2: Criação das Entidades (Model)

### O que foi feito:
1. **Criado o pacote `model`** dentro de `saude.api`

2. **Criadas 3 entidades JPA:**
   - **Exercicio.java**: Representa exercícios físicos
   - **Refeicao.java**: Representa refeições
   - **Sono.java**: Representa registros de sono

3. **Estrutura de cada entidade:**
```java
@Data                    // Lombok: gera getters, setters, toString, equals, hashCode
@Entity                  // JPA: marca como entidade do banco
@Table(name = "nome")    // JPA: define nome da tabela
public class Entidade {
    @Id                           // Define chave primária
    @GeneratedValue(...)          // ID gerado automaticamente
    private Long id;
    
    private String campo;         // Campos da tabela
}
```

### Como funciona:
1. **Você escreve a classe Java** com anotações
2. **Hibernate lê as anotações** quando a aplicação inicia
3. **Hibernate gera SQL automaticamente:**
   ```sql
   CREATE TABLE exercicio (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(255),
       series INTEGER,
       ...
   );
   ```
4. **Executa o SQL no banco H2**

### Mapeamento Java → SQL:
| Java | SQL |
|------|-----|
| `@Entity` | `CREATE TABLE` |
| `@Id` | `PRIMARY KEY` |
| `@GeneratedValue` | `AUTO_INCREMENT` |
| `private Long id` | `id BIGINT` |
| `private String nome` | `nome VARCHAR(255)` |
| `private Integer series` | `series INTEGER` |
| `private LocalDate data` | `data DATE` |

### Conceitos aprendidos:
- **JPA (Java Persistence API)**: Padrão para mapear objetos Java em tabelas
- **Hibernate**: Implementação do JPA que faz a "mágica" acontecer
- **ORM (Object-Relational Mapping)**: Mapeia objetos em tabelas
- **Lombok**: Reduz código repetitivo com anotações

### Teste realizado:
- Acessado H2 Console em `http://localhost:8080/h2-console`
- Verificado que as 3 tabelas foram criadas automaticamente
- Executadas queries SQL para ver a estrutura

---

## 📚 Aula 3: Criação dos Repositories

### O que foi feito:
1. **Criado o pacote `repository`** dentro de `saude.api`

2. **Criadas 3 interfaces Repository:**
   - **ExercicioRepository.java**
   - **RefeicaoRepository.java**
   - **SonoRepository.java**

3. **Estrutura de cada Repository:**
```java
@Repository                                    // Marca como componente Repository
public interface ExercicioRepository 
    extends JpaRepository<Exercicio, Long> {   // Herda métodos prontos
    
    // Não precisa implementar nada!
    // Spring Data JPA cria automaticamente
}
```

### Métodos herdados automaticamente:
Ao estender `JpaRepository`, você ganha de graça:

| Método | O que faz |
|--------|-----------|
| `save(entidade)` | Salva ou atualiza no banco |
| `findById(id)` | Busca por ID |
| `findAll()` | Lista todos os registros |
| `deleteById(id)` | Deleta por ID |
| `count()` | Conta total de registros |
| `existsById(id)` | Verifica se existe |

### Como funciona:
1. **Você cria a interface** (sem implementação!)
2. **Spring Data JPA cria a implementação** automaticamente em tempo de execução
3. **Você injeta o Repository** em outras classes e usa os métodos

### Exemplo de uso futuro (próximas aulas):
```java
@Service
public class ExercicioService {
    @Autowired
    private ExercicioRepository repository;
    
    public List<Exercicio> listarTodos() {
        return repository.findAll();  // Método pronto!
    }
}
```

### Conceitos aprendidos:
- **Repository Pattern**: Padrão de projeto para acesso a dados
- **Spring Data JPA**: Cria implementações automaticamente
- **Interface**: Contrato sem implementação
- **Herança de Interface**: `extends JpaRepository`
- **Generics**: `JpaRepository<Entidade, TipoDoId>`

### Por que usar Repository?
- ✅ **Menos código**: Não precisa escrever SQL
- ✅ **Padronização**: Todos os repositories seguem o mesmo padrão
- ✅ **Manutenibilidade**: Fácil de entender e manter
- ✅ **Testabilidade**: Fácil de criar mocks para testes
- ✅ **Abstração**: Separa lógica de negócio do acesso a dados

---

## 🎯 Resumo Geral até Agora

### Arquitetura em Camadas:
```
┌─────────────────────────────────────┐
│  Controller (Aula 4 - próxima)      │  ← Recebe requisições HTTP
├─────────────────────────────────────┤
│  Service (Aula 4 - próxima)         │  ← Lógica de negócio
├─────────────────────────────────────┤
│  Repository (Aula 3 - HOJE)         │  ← Acesso ao banco de dados
├─────────────────────────────────────┤
│  Model/Entity (Aula 2)              │  ← Representação das tabelas
├─────────────────────────────────────┤
│  Database H2 (Aula 1)               │  ← Banco de dados
└─────────────────────────────────────┘
```

### Fluxo de dados (será implementado nas próximas aulas):
```
Cliente (Postman/Browser)
    ↓ HTTP Request
Controller (recebe requisição)
    ↓ chama
Service (processa lógica)
    ↓ chama
Repository (acessa banco)
    ↓ executa SQL
Database (H2)
    ↓ retorna dados
Repository → Service → Controller
    ↓ HTTP Response
Cliente (recebe resposta)
```

### Estrutura de pastas atual:
```
src/main/java/saude/api/
├── api/
│   └── ApiApplication.java          (Classe principal)
├── model/
│   ├── Exercicio.java               (Entidade)
│   ├── Refeicao.java                (Entidade)
│   └── Sono.java                    (Entidade)
└── repository/
    ├── ExercicioRepository.java     (Repository)
    ├── RefeicaoRepository.java      (Repository)
    └── SonoRepository.java          (Repository)
```

### Próximos passos (Aulas 4-7):
- **Aula 4**: Criar Controllers (endpoints REST)
- **Aula 5**: Criar Services (lógica de negócio)
- **Aula 6**: Implementar validações
- **Aula 7**: Testes e documentação

---

## 📖 Glossário de Termos

- **API REST**: Interface para comunicação entre sistemas via HTTP
- **CRUD**: Create, Read, Update, Delete (operações básicas)
- **DTO**: Data Transfer Object (objeto para transferir dados)
- **Entity**: Classe que representa uma tabela do banco
- **JPA**: Java Persistence API (padrão para ORM)
- **ORM**: Object-Relational Mapping (mapeia objetos em tabelas)
- **Repository**: Camada de acesso a dados
- **Service**: Camada de lógica de negócio
- **Controller**: Camada que recebe requisições HTTP
- **Bean**: Objeto gerenciado pelo Spring
- **Dependency Injection**: Spring injeta dependências automaticamente
- **Annotation**: Anotação que adiciona metadados ao código (@Entity, @Repository, etc)
