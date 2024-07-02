package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmControllerImpl implements FilmController {
    private FilmService filmService;

    public FilmControllerImpl(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public void afficherUnFilm(int i) {
        System.out.println(filmService.consulterFilmParId(1).toString());
    }

    @Override
    public void afficherFilms() {
        filmService.consulterFilms().forEach(film -> System.out.println(film.toString()));

    }
}
