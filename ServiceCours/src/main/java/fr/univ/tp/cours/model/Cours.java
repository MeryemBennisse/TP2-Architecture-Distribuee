package fr.univ.tp.cours.model;

public class Cours {

    private Integer id;
    private String titre;
    private Integer etudiantId; // lien vers l'étudiant (géré par ServiceEtudiants)

    // Constructeur vide : utilisé par Spring pour transformer le JSON reçu en objet Cours
    public Cours() {
    }

    // Constructeur complet
    public Cours(Integer id, String titre, Integer etudiantId) {
        this.id = id;
        this.titre = titre;
        this.etudiantId = etudiantId;
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Integer etudiantId) {
        this.etudiantId = etudiantId;
    }
}