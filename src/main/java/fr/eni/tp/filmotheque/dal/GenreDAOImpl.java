package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private static final String FIND_BY_ID = "SELECT id, titre FROM genre where id = :id";
    private static final String FIND_ALL = "SELECT id, titre FROM genre";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GenreDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new BeanPropertyRowMapper<>(Genre.class));
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(FIND_ALL,  new BeanPropertyRowMapper<>(Genre.class));
    }
}
