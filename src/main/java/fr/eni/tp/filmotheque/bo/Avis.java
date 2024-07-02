package fr.eni.tp.filmotheque.bo;

public class Avis {
    private long id;
    private int note;
    private String commentaire;
    private Membre membre;
    private Film film;

    public Avis() {
    }

    public Avis(int id, int note, String commentaire, Membre membre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
