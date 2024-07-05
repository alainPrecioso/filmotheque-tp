package fr.eni.tp.filmotheque.bo;


import java.util.List;


public class Membre extends Personne {

    public Membre(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }

    private String pseudo;
    private String motDePasse;
    private boolean admin;
    private List<Avis> avis;

    public Membre(int id, String nom, String prenom, String mail, Object o) {
        super(id, nom, prenom);
        this.pseudo = mail;
    }

    public Membre() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
}
