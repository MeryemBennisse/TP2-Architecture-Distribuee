package fr.univ.tp.serviceetudiants.controller;

import fr.univ.tp.serviceetudiants.model.Etudiant;
import fr.univ.tp.serviceetudiants.repository.EtudiantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantRepository repo;

    public EtudiantController(EtudiantRepository repo) {
        this.repo = repo;
    }

    // GET /etudiants : liste de tous les étudiants
    @GetMapping
    public List<Etudiant> all() {
        return repo.findAll();
    }

    // GET /etudiants/{id} : un étudiant (404 s'il n'existe pas)
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> get(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /etudiants : créer un étudiant (201 Created)
    @PostMapping
    public ResponseEntity<Etudiant> create(@RequestBody Etudiant e) {
        e.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(e));
    }

    // PUT /etudiants/{id} : modifier un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> update(@PathVariable int id, @RequestBody Etudiant e) {
        if (repo.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        e.setId(id);
        return ResponseEntity.ok(repo.save(e));
    }

    // DELETE /etudiants/{id} : supprimer un étudiant (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repo.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }
}