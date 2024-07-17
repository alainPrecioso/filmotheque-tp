package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {

    private static final String FIND_BY_ID = """
                SELECT id, titre, annee, duree, synopsis, id_realisateur, id_genre FROM film WHERE id = :id
            """;
    private static final String FIND_ALL = "SELECT id, titre, annee, duree, synopsis, id_realisateur, id_genre FROM film";
    private static final String INSERT = """
            INSERT INTO film ( titre, annee, duree, synopsis, id_realisateur, id_genre )
            VALUES (:titre, :annee, :duree, :synopsis, :idRealisateur, :idGenre)
            """;
    private static final String FIND_TITRE = "SELECT titre FROM Film WHERE id = :id";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FilmDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Film film) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("titre", film.getTitre());
        namedParameters.addValue("annee", film.getAnnee());
        namedParameters.addValue("duree", film.getDuree());
        namedParameters.addValue("synopsis", film.getSynopsis());
        namedParameters.addValue("id_realisateur", film.getRealisateur().getId());
        namedParameters.addValue("id_genre", film.getGenre().getId());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            film.setId(keyHolder.getKey().longValue());
        }
    }

    @Override
    public Film read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new FilmRowMapper());
    }

    @Override
    public List<Film> findAll() {
        return jdbcTemplate.query(FIND_ALL, new FilmRowMapper());
    }

    @Override
    public String findTitre(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_TITRE, namedParameters, String.class);
    }

    private class FilmRowMapper implements RowMapper<Film> {
        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film(rs.getLong("id"), rs.getString("titre"), rs.getInt("annee"), rs.getInt("duree"), rs.getString("synopsis"));
            Participant realisateur = new Participant();
            realisateur.setId(rs.getLong("id_realisateur"));
            film.setRealisateur(realisateur);
            Genre genre = new Genre();
            genre.setId(rs.getLong("id_genre"));
            film.setGenre(genre);


            return film;
        }
    }
}
