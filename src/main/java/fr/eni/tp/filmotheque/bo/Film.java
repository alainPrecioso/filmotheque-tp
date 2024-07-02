package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;


public class Film {
    private long id;
    private String titre;
    private int annee;
    private int duree;
    private String synopsis;
    private Genre genre;
    private List<Avis> avis;
    private Participant realisateur;
    private List<Participant> acteurs;

    public Film() {
    }

    public Film(long id, String titre, int annee, int duree, String synopsis, Genre genre, List<Avis> avis, Participant realisateur, List<Participant> acteurs) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.genre = genre;
        this.avis = avis;
        this.realisateur = realisateur;
        this.acteurs = acteurs;
    }

    public Film(int id, String titre, int annee, int duree, String synopsis) {
        this(id, titre, annee, duree, synopsis, null, new ArrayList<>(), null, new ArrayList<>());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Participant> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", annee=" + annee +
                ", duree=" + duree +
                ", synopsis='" + synopsis + '\'' +
                ", genre=" + genre +
                ", realisateur=" + realisateur +
                ", avis=" + avis +
                ", acteurs=" + acteurs +
                '}';
    }
}
