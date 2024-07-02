package fr.eni.tp.filmotheque.bo;


import java.util.List;


public class Genre {
    private long id;
    private String titre;
    private List<Film> films;

    public Genre(int i, String genre) {
        this.id = i;
        this.titre = genre;
    }

    public Genre(long id, String titre, List<Film> films) {
        this.id = id;
        this.titre = titre;
        this.films = films;
    }

    public Genre() {
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", films=" + films +
                '}';
    }
}
