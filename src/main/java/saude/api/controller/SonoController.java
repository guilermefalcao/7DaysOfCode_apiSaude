package saude.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saude.api.model.Sono;
import saude.api.repository.SonoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST para gerenciar registros de Sono.
 * 
 * @RestController: Indica que esta classe é um controller REST (retorna JSON automaticamente)
 * @RequestMapping: Define a rota base para todos os endpoints deste controller
 */
@RestController
@RequestMapping("/sono")
public class SonoController {

    /**
     * @Autowired: Injeta automaticamente o repository (Dependency Injection)
     */
    @Autowired
    private SonoRepository repository;

    /**
     * Lista todos os registros de sono.
     * GET http://localhost:8080/sono/listar
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Sono>> listar() {
        List<Sono> registros = repository.findAll();
        return ResponseEntity.ok(registros);
    }

    /**
     * Salva um novo registro de sono.
     * POST http://localhost:8080/sono/salvar
     * Body: {"horasDormidas": 8.0, "qualidade": "boa", "data": "2026-01-26"}
     */
    @PostMapping("/salvar")
    public ResponseEntity<Sono> salvar(@RequestBody Sono sono) {
        Sono sonoSalvo = repository.save(sono);
        return ResponseEntity.status(HttpStatus.CREATED).body(sonoSalvo);
    }

    /**
     * Atualiza um registro de sono existente.
     * PUT http://localhost:8080/sono/editar/1
     * Body: {"horasDormidas": 7.5, "qualidade": "moderada", "data": "2026-01-26"}
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<Sono> editar(@PathVariable Long id, @RequestBody Sono sonoAtualizado) {
        Optional<Sono> sonoExistente = repository.findById(id);
        
        if (sonoExistente.isPresent()) {
            sonoAtualizado.setId(id);
            Sono sonoSalvo = repository.save(sonoAtualizado);
            return ResponseEntity.ok(sonoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um registro de sono por ID.
     * DELETE http://localhost:8080/sono/excluir/1
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
