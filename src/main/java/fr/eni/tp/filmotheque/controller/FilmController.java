package fr.eni.tp.filmotheque.controller;

import org.springframework.ui.Model;

public interface FilmController {
    String afficherUnFilm(int i, Model model);

    String afficherFilms(Model model);
}
