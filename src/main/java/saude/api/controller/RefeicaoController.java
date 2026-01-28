package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Refeicao;
import saude.api.repository.RefeicaoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST para gerenciar Refeições.
 * 
 * @RestController: Indica que esta classe é um controller REST (retorna JSON automaticamente)
 * @RequestMapping: Define a rota base para todos os endpoints deste controller
 */
@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {

    /**
     * @Autowired: Injeta automaticamente o repository (Dependency Injection)
     */
    @Autowired
    private RefeicaoRepository repository;

    /**
     * Lista todas as refeições.
     * GET http://localhost:8080/refeicao/listar
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Refeicao>> listar() {
        List<Refeicao> refeicoes = repository.findAll();
        return ResponseEntity.ok(refeicoes);
    }

    /**
     * Salva uma nova refeição.
     * POST http://localhost:8080/refeicao/salvar
     * Body: {"nome": "Frango com batata", "tipo": "almoço", "quantidade": 350.0, "data": "2026-01-26"}
     */
    @PostMapping("/salvar")
    public ResponseEntity<Refeicao> salvar(@RequestBody Refeicao refeicao) {
        Refeicao refeicaoSalva = repository.save(refeicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoSalva);
    }

    /**
     * Atualiza uma refeição existente.
     * PUT http://localhost:8080/refeicao/editar/1
     * Body: {"nome": "Peixe com arroz", "tipo": "jantar", "quantidade": 300.0, "data": "2026-01-26"}
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<Refeicao> editar(@PathVariable Long id, @RequestBody Refeicao refeicaoAtualizada) {
        Optional<Refeicao> refeicaoExistente = repository.findById(id);
        
        if (refeicaoExistente.isPresent()) {
            refeicaoAtualizada.setId(id);
            Refeicao refeicaoSalva = repository.save(refeicaoAtualizada);
            return ResponseEntity.ok(refeicaoSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui uma refeição por ID.
     * DELETE http://localhost:8080/refeicao/excluir/1
     */
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
