# Guia de Testes no Postman - API Saúde

## 🚀 Como Testar

### 1. Inicie a aplicação
```bash
./mvnw spring-boot:run
```

---

## 📋 EXERCÍCIO

### ✅ Listar todos os exercícios
- **Método:** GET
- **URL:** `http://localhost:8080/exercicio/listar`
- **Body:** Nenhum
- **Resposta esperada:** `200 OK` com array de exercícios (vazio inicialmente)

---

### ✅ Criar um exercício
- **Método:** POST
- **URL:** `http://localhost:8080/exercicio/salvar`
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**
```json
{
  "nome": "Supino",
  "series": 4,
  "repeticoes": 12,
  "carga": 80.0,
  "tempo": 45,
  "data": "2026-01-26"
}
```
- **Resposta esperada:** `201 CREATED` com o exercício criado (incluindo ID)

---

### ✅ Atualizar um exercício
- **Método:** PUT
- **URL:** `http://localhost:8080/exercicio/editar/1` (substitua 1 pelo ID real)
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**
```json
{
  "nome": "Supino Inclinado",
  "series": 3,
  "repeticoes": 10,
  "carga": 70.0,
  "tempo": 40,
  "data": "2026-01-26"
}
```
- **Resposta esperada:** `200 OK` com o exercício atualizado

---

### ✅ Excluir um exercício
- **Método:** DELETE
- **URL:** `http://localhost:8080/exercicio/excluir/1` (substitua 1 pelo ID real)
- **Body:** Nenhum
- **Resposta esperada:** `204 NO CONTENT` (sem corpo na resposta)

---

## 🍽️ REFEIÇÃO

### ✅ Listar todas as refeições
- **Método:** GET
- **URL:** `http://localhost:8080/refeicao/listar`

---

### ✅ Criar uma refeição
- **Método:** POST
- **URL:** `http://localhost:8080/refeicao/salvar`
- **Body (raw JSON):**
```json
{
  "nome": "Frango com batata doce",
  "tipo": "almoço",
  "quantidade": 350.0,
  "data": "2026-01-26"
}
```

---

### ✅ Atualizar uma refeição
- **Método:** PUT
- **URL:** `http://localhost:8080/refeicao/editar/1`
- **Body (raw JSON):**
```json
{
  "nome": "Peixe com arroz integral",
  "tipo": "jantar",
  "quantidade": 300.0,
  "data": "2026-01-26"
}
```

---

### ✅ Excluir uma refeição
- **Método:** DELETE
- **URL:** `http://localhost:8080/refeicao/excluir/1`

---

## 😴 SONO

### ✅ Listar todos os registros de sono
- **Método:** GET
- **URL:** `http://localhost:8080/sono/listar`

---

### ✅ Criar um registro de sono
- **Método:** POST
- **URL:** `http://localhost:8080/sono/salvar`
- **Body (raw JSON):**
```json
{
  "horasDormidas": 8.0,
  "qualidade": "boa",
  "data": "2026-01-26"
}
```

---

### ✅ Atualizar um registro de sono
- **Método:** PUT
- **URL:** `http://localhost:8080/sono/editar/1`
- **Body (raw JSON):**
```json
{
  "horasDormidas": 7.5,
  "qualidade": "moderada",
  "data": "2026-01-26"
}
```

---

### ✅ Excluir um registro de sono
- **Método:** DELETE
- **URL:** `http://localhost:8080/sono/excluir/1`

---

## 🎯 Fluxo de Teste Completo

1. **Criar** um exercício (POST /exercicio/salvar)
2. **Listar** todos (GET /exercicio/listar) - deve aparecer o exercício criado
3. **Atualizar** o exercício (PUT /exercicio/editar/1)
4. **Listar** novamente - deve aparecer atualizado
5. **Excluir** o exercício (DELETE /exercicio/excluir/1)
6. **Listar** novamente - deve estar vazio

Repita o mesmo fluxo para Refeição e Sono!

---

## 📊 Códigos de Status HTTP

- `200 OK`: Requisição bem-sucedida (GET, PUT)
- `201 CREATED`: Recurso criado com sucesso (POST)
- `204 NO CONTENT`: Recurso excluído com sucesso (DELETE)
- `404 NOT FOUND`: Recurso não encontrado (PUT/DELETE com ID inexistente)

---

## 💡 Dicas

1. No Postman, crie uma **Collection** chamada "API Saúde"
2. Dentro dela, crie **3 pastas**: Exercício, Refeição, Sono
3. Salve cada requisição dentro da pasta correspondente
4. Use **variáveis** para o ID: `{{exercicioId}}`
5. Após criar um recurso, copie o ID retornado para usar nas outras requisições
