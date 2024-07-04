package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("details")
    public String afficherUnFilm(@RequestParam("id") int id, Model model) {
        Film film = filmService.consulterFilmParId(id);
        model.addAttribute("film", film);
        return "details";
    }

    @GetMapping("details")
    public String afficherUnFilmGet(@RequestParam(name = "id") int id, Model model) {
        Film film = filmService.consulterFilmParId(id);
        model.addAttribute("film", film);
        return "details";
    }

    @GetMapping("details/{id}")
    public String afficherUnFilmUrl(@PathVariable("id") int id, Model model) {
        Film film = filmService.consulterFilmParId(id);
        model.addAttribute("film", film);
        return "details";
    }

    @GetMapping
    public String afficherFilms(Model model) {
        List<Film> films = filmService.consulterFilms();
        model.addAttribute("films", films);
        return "liste";
    }
}
