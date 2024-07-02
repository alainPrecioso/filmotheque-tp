package fr.eni.tp.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Participant extends Personne {

    public Participant() {
        super();
    }

    public Participant(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }


}
