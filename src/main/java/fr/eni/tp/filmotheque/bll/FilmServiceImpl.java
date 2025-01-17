package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmDAO;
import fr.eni.tp.filmotheque.dal.GenreDAO;
import fr.eni.tp.filmotheque.dal.ParticipantDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDAO filmDAO;
    private final GenreDAO genreDAO;
    private final ParticipantDAO participantDAO;

    public FilmServiceImpl(FilmDAO filmDAO, GenreDAO genreDAO, ParticipantDAO participantDAO) {
        this.filmDAO = filmDAO;
        this.genreDAO = genreDAO;
        this.participantDAO = participantDAO;
    }


    @Override
    public List<Film> consulterFilms() {
        List<Film> films = filmDAO.findAll();
        films.forEach(this::chargerGenreRealisateurEtActeursFilm);
        return films;
    }

    @Override
    public Film consulterFilmParId(long id) {
        Film film = filmDAO.read(id);
        chargerGenreRealisateurEtActeursFilm(film);
        return film;
    }

    @Override
    public void chargerGenreRealisateurEtActeursFilm(Film film) {
        film.setActeurs(participantDAO.findActeurs(film.getId()));
        film.setRealisateur(participantDAO.read(film.getRealisateur().getId()));
        film.setGenre(genreDAO.read(film.getRealisateur().getId()));
    }

    @Override
    public List<Genre> consulterGenres() {
        return genreDAO.findAll();
    }

    @Override
    public List<Participant> consulterParticipants() {
        return participantDAO.findAll();
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return genreDAO.read(id);
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        return participantDAO.read(id);
    }

    @Override
    public void creerFilm(Film film) {
        filmDAO.create(film);
    }

    @Override
    public String consulterTitreFilm(long id) {
        return filmDAO.findTitre(id);
    }

    @Override
    public void publierAvis(Avis avis, long idFilm) {

    }

    @Override
    public List<Avis> consulterAvis(long idFilm) {
        return List.of();
    }
}
