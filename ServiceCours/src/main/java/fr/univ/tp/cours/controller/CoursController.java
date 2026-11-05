package fr.univ.tp.cours.controller;

import fr.univ.tp.cours.model.Cours;
import fr.univ.tp.cours.model.EtudiantDto;
import fr.univ.tp.cours.repository.CoursRepository;
import fr.univ.tp.cours.service.EtudiantClient;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cours")
public class CoursController {
    private final CoursRepository repo;
    private final EtudiantClient etudiantClient;

    public CoursController(CoursRepository repo, EtudiantClient etudiantClient) {
        this.repo = repo;
        this.etudiantClient = etudiantClient;
    }

    @GetMapping
    public List<Cours> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Cours get(@PathVariable int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Cours non trouvé"));
    }

    @PostMapping
    public Cours create(@RequestBody Cours c) { return repo.save(c); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) { repo.delete(id); }

    @GetMapping("/enrichis")
    public List<Map<String,Object>> getCoursEnrichis() {
        List<Cours> cours = repo.findAll();
        List<Map<String,Object>> result = new ArrayList<>();
        for (Cours c : cours) {
            Map<String,Object> m = new HashMap<>();
            m.put("id", c.getId());
            m.put("titre", c.getTitre());
            try {
                EtudiantDto et = etudiantClient.getEtudiantById(c.getEtudiantId());
                m.put("etudiantNom", et.getNom());
            } catch (Exception ex) {
                m.put("etudiantNom", "INCONNU");
            }
            result.add(m);
        }
        return result;
    }
}