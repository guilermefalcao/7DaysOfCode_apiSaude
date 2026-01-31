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

## 📚 Aula 4: Criação dos Controllers (API REST)

### O que foi feito:
1. **Criado o pacote `controller`** dentro de `saude.api`

2. **Criados 3 Controllers REST:**
   - **ExercicioController.java**
   - **RefeicaoController.java**
   - **SonoController.java**

3. **Estrutura de cada Controller:**
```java
@RestController                    // Retorna JSON automaticamente
@RequestMapping("/exercicio")      // Rota base
public class ExercicioController {
    
    @Autowired                     // Injeta o repository
    private ExercicioRepository repository;
    
    @GetMapping("/listar")          // GET /exercicio/listar
    @PostMapping("/salvar")         // POST /exercicio/salvar
    @PutMapping("/editar/{id}")     // PUT /exercicio/editar/1
    @DeleteMapping("/excluir/{id}") // DELETE /exercicio/excluir/1
}
```

### Endpoints criados:

| Método | Rota | O que faz |
|--------|------|----------|
| GET | `/exercicio/listar` | Lista todos os exercícios |
| POST | `/exercicio/salvar` | Cria um novo exercício |
| PUT | `/exercicio/editar/{id}` | Atualiza um exercício |
| DELETE | `/exercicio/excluir/{id}` | Exclui um exercício |

### Anotações usadas:
- `@RestController`: Marca a classe como controller REST (retorna JSON)
- `@RequestMapping`: Define a rota base
- `@GetMapping`: Mapeia requisições GET
- `@PostMapping`: Mapeia requisições POST
- `@PutMapping`: Mapeia requisições PUT
- `@DeleteMapping`: Mapeia requisições DELETE
- `@PathVariable`: Captura variáveis da URL (ex: {id})
- `@RequestBody`: Converte JSON em objeto Java
- `@Autowired`: Injeta o repository automaticamente

### Como testar:
**Postman:**
- GET: `http://localhost:8080/exercicio/listar`
- POST: `http://localhost:8080/exercicio/salvar` + JSON no body
- PUT: `http://localhost:8080/exercicio/editar/1` + JSON no body
- DELETE: `http://localhost:8080/exercicio/excluir/1`

### Conceitos aprendidos:
- **REST**: Padrão de arquitetura para APIs
- **HTTP Methods**: GET, POST, PUT, DELETE
- **JSON**: Formato de dados para comunicação
- **ResponseEntity**: Controla status HTTP da resposta
- **Status HTTP**: 200 OK, 201 CREATED, 204 NO CONTENT, 404 NOT FOUND

---

## 📚 Aula 5: Integração Frontend com Thymeleaf

### O que foi feito:
1. **Criados 2 Controllers MVC:**
   - **HomeController.java**: Exibe página inicial
   - **CrudController.java**: Gerencia CRUD via páginas HTML

2. **Criadas 2 páginas HTML:**
   - **index.html**: Página inicial bonita e responsiva
   - **crud.html**: Interface completa de CRUD com tabela e modais

3. **Estrutura do CrudController:**
```java
@Controller                        // Retorna HTML (não JSON!)
@RequestMapping("/crud")
public class CrudController {
    
    @Autowired
    private ExercicioRepository repository;
    
    @GetMapping                    // GET /crud
    public String exibirCrud(Model model) {
        model.addAttribute("exercicios", repository.findAll());
        return "crud";             // Retorna templates/crud.html
    }
    
    @PostMapping("/salvar")         // POST /crud/salvar
    public String salvar(@ModelAttribute Exercicio exercicio) {
        repository.save(exercicio);
        return "redirect:/crud";    // Redireciona para /crud
    }
}
```

### Diferença entre Controllers:

| @RestController | @Controller |
|-----------------|-------------|
| Retorna JSON | Retorna HTML |
| Para APIs | Para páginas web |
| Usado na Aula 4 | Usado na Aula 5 |
| Postman/Mobile | Navegador |

### Thymeleaf - Principais recursos:

```html
<!-- Loop pelos dados -->
<tr th:each="exercicio : ${exercicios}">

<!-- Exibir valor -->
<td th:text="${exercicio.nome}">Supino</td>

<!-- Condição -->
<tr th:if="${#lists.isEmpty(exercicios)}">

<!-- Link dinâmico -->
<a th:href="@{/crud/excluir/{id}(id=${exercicio.id})}">

<!-- Formatar data -->
<td th:text="${#temporals.format(exercicio.data, 'dd/MM/yyyy')}">

<!-- Formulário -->
<form th:action="@{/crud/salvar}" method="post">
    <input type="text" name="nome">
</form>
```

### Funcionalidades implementadas:
- ✅ Listar exercícios em tabela
- ✅ Adicionar exercício via modal
- ✅ Editar exercício (JavaScript preenche modal automaticamente)
- ✅ Excluir exercício com confirmação
- ✅ Design responsivo e moderno

### JavaScript usado:
```javascript
// Abre modal de edição e preenche campos
function abrirModalEditar(id, nome, series, repeticoes, carga, tempo, data) {
    document.getElementById('editId').value = id;
    document.getElementById('editNome').value = nome;
    // ... preenche outros campos
    document.getElementById('modalEditar').classList.add('active');
}
```

### Como acessar:
- **Página inicial:** http://localhost:8080/
- **CRUD:** http://localhost:8080/crud
- **H2 Console:** http://localhost:8080/h2-console
- **API REST:** http://localhost:8080/exercicio/listar

### Conceitos aprendidos:
- **Thymeleaf**: Template engine para renderizar HTML dinâmico
- **Model**: Objeto para passar dados do Controller para a View
- **@ModelAttribute**: Converte dados do formulário em objeto Java
- **redirect**: Redireciona para outra rota após ação
- **Modal**: Janela popup para formulários
- **MVC**: Model-View-Controller (padrão de arquitetura)

### Fluxo completo:
```
Usuário clica "Adicionar"
    ↓
Modal abre com formulário
    ↓
Usuário preenche e clica "Salvar"
    ↓
POST /crud/salvar
    ↓
CrudController.salvar()
    ↓
Repository.save()
    ↓
Banco H2 salva
    ↓
Redirect /crud
    ↓
Página recarrega com novo exercício na tabela
```

---

## 🎯 Arquitetura Final (Aulas 1-5)

```
┌─────────────────────────────────────┐
│  View (HTML + Thymeleaf) - Aula 5  │  ← Interface do usuário
├─────────────────────────────────────┤
│  Controller MVC - Aula 5            │  ← Renderiza páginas HTML
├─────────────────────────────────────┤
│  Controller REST - Aula 4           │  ← API JSON (Postman)
├─────────────────────────────────────┤
│  Repository - Aula 3                │  ← Acesso ao banco
├─────────────────────────────────────┤
│  Model/Entity - Aula 2              │  ← Representação das tabelas
├─────────────────────────────────────┤
│  Database H2 - Aula 1               │  ← Banco em memória
└─────────────────────────────────────┘
```

### Estrutura de pastas completa:
```
src/main/
├── java/saude/api/
│   ├── api/
│   │   └── ApiApplication.java
│   ├── controller/
│   │   ├── ExercicioController.java  (REST - JSON)
│   │   ├── RefeicaoController.java   (REST - JSON)
│   │   ├── SonoController.java       (REST - JSON)
│   │   ├── HomeController.java       (MVC - HTML)
│   │   └── CrudController.java       (MVC - HTML)
│   ├── model/
│   │   ├── Exercicio.java
│   │   ├── Refeicao.java
│   │   └── Sono.java
│   └── repository/
│       ├── ExercicioRepository.java
│       ├── RefeicaoRepository.java
│       └── SonoRepository.java
└── resources/
    ├── templates/
    │   ├── index.html
    │   └── crud.html
    └── application.properties
```

### Três formas de acessar os mesmos dados:

| Forma | URL | Tecnologia | Retorna |
|-------|-----|------------|----------|
| Interface Web | http://localhost:8080/crud | Thymeleaf | HTML |
| API REST | http://localhost:8080/exercicio/listar | Spring REST | JSON |
| Banco Direto | http://localhost:8080/h2-console | H2 Console | SQL |

### Próximos passos (Aulas 6-7):
- **Aula 6**: Validações e tratamento de erros
- **Aula 7**: Testes e documentação

---

## 📚 Aula 6: Expansão do Frontend (Refeições e Sono)

### O que foi feito:
1. **Criados 2 novos Controllers MVC:**
   - **RefeicaoCrudController.java**: Gerencia CRUD de Refeições via HTML
   - **SonoCrudController.java**: Gerencia CRUD de Sono via HTML

2. **Criadas 2 novas páginas HTML:**
   - **refeicao-crud.html**: Interface completa para gerenciar refeições
   - **sono-crud.html**: Interface completa para gerenciar sono

3. **Atualizada página inicial:**
   - **index.html**: Agora com botões para acessar os 3 módulos

### Estrutura dos novos Controllers:
```java
@Controller
@RequestMapping("/refeicao-crud")
public class RefeicaoCrudController {
    @Autowired
    private RefeicaoRepository repository;
    
    @GetMapping                    // Exibe página
    @PostMapping("/salvar")         // Adiciona
    @PostMapping("/editar")         // Atualiza
    @GetMapping("/excluir/{id}")   // Exclui
}
```

### Funcionalidades implementadas:

**Refeições:**
- ✅ Listar todas as refeições em tabela
- ✅ Adicionar refeição (nome, tipo, quantidade, data)
- ✅ Editar refeição via modal preenchido automaticamente
- ✅ Excluir refeição com confirmação
- ✅ Select com opções: Café da manhã, Almoço, Lanche, Jantar, Ceia

**Sono:**
- ✅ Listar todos os registros de sono em tabela
- ✅ Adicionar registro (horas dormidas, qualidade, data)
- ✅ Editar registro via modal preenchido automaticamente
- ✅ Excluir registro com confirmação
- ✅ Select com opções: Excelente, Boa, Moderada, Ruim, Péssima

### Reutilização de código:
As páginas de Refeição e Sono seguem o mesmo padrão de Exercício:
- Mesma estrutura HTML
- Mesmo JavaScript para modais
- Mesma lógica de CRUD
- Apenas adaptadas para os campos específicos de cada entidade

### Navegação:
```
Página Inicial (index.html)
    │
    ├── Botão "Exercícios" → /crud
    ├── Botão "Refeições" → /refeicao-crud
    └── Botão "Sono" → /sono-crud

Cada página CRUD tem:
    └── Botão "Voltar para Home" → /
```

### Cores temáticas:
- **Exercícios:** Roxo (#667eea)
- **Refeições:** Verde (#28a745)
- **Sono:** Roxo escuro (#6f42c1)

### Como testar:
1. Inicie a aplicação: `./mvnw spring-boot:run`
2. Acesse: http://localhost:8080/
3. Clique em "Refeições" ou "Sono"
4. Teste adicionar, editar e excluir registros
5. Verifique no H2 Console: http://localhost:8080/h2-console

### Conceitos reforçados:
- **Reutilização de código**: Mesmo padrão para todas as entidades
- **Consistência**: Interface uniforme e intuitiva
- **Separação de responsabilidades**: Cada entidade tem seu próprio controller e página
- **UX**: Navegação fácil entre módulos

---

## 🎯 Sistema Completo (Aulas 1-6)

### Estrutura final de controllers:
```
controller/
├── HomeController.java              (Página inicial)
├── CrudController.java              (CRUD Exercícios - HTML)
├── RefeicaoCrudController.java      (CRUD Refeições - HTML)
├── SonoCrudController.java          (CRUD Sono - HTML)
├── ExercicioController.java         (API REST - JSON)
├── RefeicaoController.java          (API REST - JSON)
└── SonoController.java              (API REST - JSON)
```

### Estrutura final de templates:
```
templates/
├── index.html                       (Página inicial)
├── crud.html                        (CRUD Exercícios)
├── refeicao-crud.html               (CRUD Refeições)
└── sono-crud.html                   (CRUD Sono)
```

### Formas de acessar os dados:

| Módulo | Interface Web | API REST | Banco H2 |
|--------|---------------|----------|----------|
| Exercícios | /crud | /exercicio/listar | SELECT * FROM EXERCICIO |
| Refeições | /refeicao-crud | /refeicao/listar | SELECT * FROM REFEICAO |
| Sono | /sono-crud | /sono/listar | SELECT * FROM SONO |

### Projeto completo:
✅ 3 Entidades (Model)
✅ 3 Repositories
✅ 3 Controllers REST (API JSON)
✅ 3 Controllers MVC (Páginas HTML)
✅ 4 Páginas HTML com Thymeleaf
✅ CRUD completo para todas as entidades
✅ Interface web moderna e responsiva
✅ Navegação intuitiva
✅ JavaScript para modais dinâmicos

---

## 📚 Aula 7: Relatórios e Gráficos com Chart.js

### O que foi feito:
1. **Criado RelatorioService** para cálculos estatísticos
2. **Adicionadas queries @Query** nos repositories
3. **Criado RelatorioController** para exibir relatórios
4. **Criada página relatorios.html** com 4 gráficos Chart.js

### Queries personalizadas:
```java
@Query("SELECT COALESCE(AVG(e.tempo), 0.0) FROM Exercicio e")
Double calcularMediaTempo();
```

### Gráficos implementados:
- 📊 Barras (Exercícios)
- 🥧 Pizza (Refeições)
- 📈 Linha (Sono)
- 🎯 Radar (Visão Geral)

### Como testar:
1. Inicie: `./mvnw spring-boot:run`
2. Cadastre dados nos CRUDs
3. Acesse: http://localhost:8080/relatorios
4. Visualize gráficos interativos

**Projeto #7DaysOfCode concluído! 🎉**

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
