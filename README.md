# 7DaysOfCode - API Saúde

Projeto desenvolvido durante o desafio #7DaysOfCode da Alura.

## Descrição
API para registro de saúde diária, permitindo gerenciar dados relacionados à saúde de forma eficiente.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.1
- Spring Web
- Spring Data JPA
- Thymeleaf
- H2 Database
- Spring Boot Actuator
- Spring Boot DevTools
- Lombok
- Maven

## Estrutura do Projeto

### Entidades (Model)
Criadas 3 entidades JPA que representam as tabelas no banco de dados:

- **Exercicio**: Registra atividades físicas
  - `id` (Long): Identificador único
  - `data` (LocalDate): Data do exercício
  - `nome` (String): Nome do exercício
  - `series` (Integer): Número de séries
  - `repeticoes` (Integer): Número de repetições
  - `carga` (Double): Carga utilizada
  - `tempo` (Integer): Tempo de duração

- **Refeicao**: Registra refeições
  - `id` (Long): Identificador único
  - `nome` (String): Nome da refeição
  - `tipo` (String): Tipo (café, almoço, jantar, etc)
  - `quantidade` (Double): Quantidade em gramas
  - `data` (LocalDate): Data da refeição

- **Sono**: Registra qualidade do sono
  - `id` (Long): Identificador único
  - `horasDormidas` (Double): Quantidade de horas dormidas
  - `qualidade` (String): Qualidade do sono (boa, moderada, ruim)
  - `data` (LocalDate): Data do registro

### Repositories
Criadas 3 interfaces Repository que estendem JpaRepository:

- **ExercicioRepository**: Gerencia operações CRUD de Exercicio
- **RefeicaoRepository**: Gerencia operações CRUD de Refeicao
- **SonoRepository**: Gerencia operações CRUD de Sono

Cada repository herda automaticamente métodos como:
- `save()`: Salvar/atualizar
- `findById()`: Buscar por ID
- `findAll()`: Listar todos
- `deleteById()`: Deletar por ID
- `count()`: Contar registros
- `existsById()`: Verificar existência

### Anotações Utilizadas
- `@Entity`: Marca a classe como entidade JPA
- `@Table`: Define o nome da tabela no banco
- `@Data`: Lombok - gera getters, setters, toString, equals e hashCode automaticamente
- `@Id`: Define a chave primária
- `@GeneratedValue`: Gera o ID automaticamente (auto-increment)
- `@Repository`: Marca a interface como componente Repository do Spring

## Configuração do Ambiente
O projeto utiliza banco de dados H2 em memória para persistência de dados durante o desenvolvimento.

### Acesso ao H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (deixar em branco)

## Como Executar
```bash
./mvnw spring-boot:run
```

## Como Acessar
- **Página Inicial:** http://localhost:8080/
- **CRUD de Exercícios:** http://localhost:8080/crud
- **CRUD de Refeições:** http://localhost:8080/refeicao-crud
- **CRUD de Sono:** http://localhost:8080/sono-crud
- **H2 Console:** http://localhost:8080/h2-console
- **API REST Exercícios:** http://localhost:8080/exercicio/listar
- **API REST Refeições:** http://localhost:8080/refeicao/listar
- **API REST Sono:** http://localhost:8080/sono/listar

## Como Testar
1. Execute a aplicação com `./mvnw spring-boot:run`
2. Acesse http://localhost:8080/h2-console
3. Configure a conexão:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: (vazio)
4. Execute as queries SQL:
```sql
SHOW TABLES;
SELECT * FROM EXERCICIO;
SELECT * FROM REFEICAO;
SELECT * FROM SONO;
```

## Progresso do Desafio
- ✅ Aula 1: Configuração inicial do projeto
- ✅ Aula 2: Criação das entidades (Exercicio, Refeicao, Sono)
- ✅ Aula 3: Criação dos Repositories
- ✅ Aula 4: Criação dos Controllers (API REST)
- ✅ Aula 5: Integração Frontend com Thymeleaf (Exercícios)
  - Criado HomeController e CrudController
  - Desenvolvidas páginas index.html e crud.html
  - Implementado CRUD completo via interface web
  - JavaScript para modais de edição
- ✅ Aula 6: Expansão do Frontend (Refeições e Sono)
  - Criados RefeicaoCrudController e SonoCrudController
  - Desenvolvidas páginas refeicao-crud.html e sono-crud.html
  - CRUD completo para todas as 3 entidades
  - Interface unificada com navegação entre módulos

## Arquitetura
```
Controller (Aula 4) ← Recebe requisições HTTP (GET, POST, PUT, DELETE)
    ↓
Repository (Aula 3) ← Acesso ao banco de dados
    ↓
Model/Entity (Aula 2) ← Representação das tabelas
    ↓
Database H2 (Aula 1) ← Banco de dados em memória
```

## Endpoints da API

### Exercício
- `GET /exercicio/listar` - Lista todos os exercícios
- `POST /exercicio/salvar` - Cria um novo exercício
- `PUT /exercicio/editar/{id}` - Atualiza um exercício
- `DELETE /exercicio/excluir/{id}` - Exclui um exercício

### Refeição
- `GET /refeicao/listar` - Lista todas as refeições
- `POST /refeicao/salvar` - Cria uma nova refeição
- `PUT /refeicao/editar/{id}` - Atualiza uma refeição
- `DELETE /refeicao/excluir/{id}` - Exclui uma refeição

### Sono
- `GET /sono/listar` - Lista todos os registros de sono
- `POST /sono/salvar` - Cria um novo registro de sono
- `PUT /sono/editar/{id}` - Atualiza um registro de sono
- `DELETE /sono/excluir/{id}` - Exclui um registro de sono

## Como Testar com Postman
Veja o arquivo [TESTES_POSTMAN.md](TESTES_POSTMAN.md) para guia completo de testes.

## Autor
Guilherme Falcão

## Curso
Alura - #7DaysOfCode
