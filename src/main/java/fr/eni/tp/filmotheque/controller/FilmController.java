package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/films")
@SessionAttributes({"loggedMembre" })
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

    @GetMapping("{id}")
    public String afficherUnFilmUrl(@PathVariable("id") int id, Model model) {
        Film film = filmService.consulterFilmParId(id);
        model.addAttribute("film", film);
        return "details";
    }

    @GetMapping("{id}/avis")
    public String avisFilm(@PathVariable("id") int id,
                           @ModelAttribute("loggedMembre") Optional<Membre> loggedMembre,
                           Model model) {
        if (loggedMembre.isPresent() && loggedMembre.get().getId() >= 1) {
            if (id > 0) {
                Film film = filmService.consulterFilmParId(id);
                model.addAttribute("film", film);
                model.addAttribute("avis", new Avis());
                return "avis";
            }
        }
        return "redirect:/films";
    }

    @PostMapping("{id}/avis")
    public String creeAvis(@PathVariable("id") int id,
                           @ModelAttribute("loggedMembre") Optional<Membre> loggedMembre,
                           @ModelAttribute("avis") Avis avis) {
        if (loggedMembre.isPresent() && loggedMembre.get().getId() >= 1) {
            avis.setMembre(loggedMembre.get());
            filmService.publierAvis(avis, id);
        }
        return "redirect:/films";
    }

    @GetMapping
    public String afficherFilms(Model model) {
        List<Film> films = filmService.consulterFilms();
        model.addAttribute("films", films);
        return "liste";
    }

    @ModelAttribute("genres")
    public List<Genre> chargerGenres() {
        return filmService.consulterGenres();
    }

    @GetMapping("/creer")
    public String creerFilmView(@ModelAttribute("loggedMembre") Optional<Membre> loggedMembre,
                                Model model) {
        if (loggedMembre.isPresent() && loggedMembre.get().getId() >= 1) {
            Film film = new Film();
            film.setGenre(new Genre());
            film.setRealisateur(new Participant());
            model.addAttribute("genre", new Genre());
            model.addAttribute("film", film);
            return "creation";
        }
        return "redirect:/films";
    }

    @PostMapping("/creer")
    public String creerFilm(@ModelAttribute("loggedMembre") Optional<Membre> loggedMembre,
                            @ModelAttribute("film") Film film) {
        if (loggedMembre.isPresent() && loggedMembre.get().getId() >= 1) {
            film.setGenre(filmService.consulterGenreParId(film.getGenre().getId()));
            filmService.creerFilm(film);
        }
        return "redirect:/films";
    }
}
