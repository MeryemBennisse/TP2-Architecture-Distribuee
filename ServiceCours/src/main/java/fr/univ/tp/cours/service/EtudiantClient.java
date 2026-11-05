package fr.univ.tp.cours.service;

import fr.univ.tp.cours.model.EtudiantDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EtudiantClient {
    private final RestTemplate rest = new RestTemplate();

    @Value("${etudiants.url}")
    private String baseUrl;

    public EtudiantDto getEtudiantById(int id) {
        return rest.getForObject(baseUrl + "/" + id, EtudiantDto.class);
    }
}