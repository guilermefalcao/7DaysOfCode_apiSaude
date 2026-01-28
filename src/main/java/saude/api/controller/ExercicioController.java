package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Exercicio;
import saude.api.repository.ExercicioRepository;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST para gerenciar Exercícios.
 * 
 * @RestController: Indica que esta classe é um controller REST (retorna JSON automaticamente)
 * @RequestMapping: Define a rota base para todos os endpoints deste controller
 */
@RestController
@RequestMapping("/exercicio")
public class ExercicioController {

    /**
     * @Autowired: Injeta automaticamente o repository (Dependency Injection)
     */
    @Autowired
    private ExercicioRepository repository;

    /**
     * Lista todos os exercícios.
     * GET http://localhost:8080/exercicio/listar
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Exercicio>> listar() {
        List<Exercicio> exercicios = repository.findAll();
        return ResponseEntity.ok(exercicios);
    }

    /**
     * Salva um novo exercício.
     * POST http://localhost:8080/exercicio/salvar
     * Body: {"nome": "Supino", "series": 4, "repeticoes": 12, "carga": 80.0, "tempo": 45, "data": "2026-01-26"}
     */
    @PostMapping("/salvar")
    public ResponseEntity<Exercicio> salvar(@RequestBody Exercicio exercicio) {
        Exercicio exercicioSalvo = repository.save(exercicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioSalvo);
    }

    /**
     * Atualiza um exercício existente.
     * PUT http://localhost:8080/exercicio/editar/1
     * Body: {"nome": "Supino Inclinado", "series": 3, "repeticoes": 10, "carga": 70.0, "tempo": 40, "data": "2026-01-26"}
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<Exercicio> editar(@PathVariable Long id, @RequestBody Exercicio exercicioAtualizado) {
        Optional<Exercicio> exercicioExistente = repository.findById(id);
        
        if (exercicioExistente.isPresent()) {
            exercicioAtualizado.setId(id);
            Exercicio exercicioSalvo = repository.save(exercicioAtualizado);
            return ResponseEntity.ok(exercicioSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um exercício por ID.
     * DELETE http://localhost:8080/exercicio/excluir/1
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
