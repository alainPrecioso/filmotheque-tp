package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;

import java.util.List;

public interface FilmDAO {

    void create(Film film);

    Film read(long id);

    List<Film> findAll();

    String findTitre(long id);
}
