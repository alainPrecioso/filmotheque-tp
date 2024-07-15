package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.bo.Personne;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {

    private static final String FIND_BY_ID = "SELECT id, nom, prenom FROM participant where id = :id";
    private static final String FIND_ALL = "SELECT id, nom, prenom FROM participant";
    private static final String INSERT = """
        INSERT INTO acteurs( id_film, id_participant)
        VALUES (:idFilm, :idParticipant)
        """;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ParticipantDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Participant read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new BeanPropertyRowMapper<>(Participant.class));
    }

    @Override
    public List<Participant> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Participant.class));
    }

    @Override
    public List<Participant> findActeurs(long id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
                .withProcedureName("FindActeurs")
                .returningResultSet("acteurs", new BeanPropertyRowMapper<>(Participant.class));
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("idFilm", id);

        Map<String, Object> out = jdbcCall.execute(in);

        if (out.get("acteurs") != null) {
            List<Participant> acteurs = (List<Participant>) out.get("acteurs");
            return acteurs;
        }

        return null;
    }

    @Override
    public void createActeur(long idParticipant, long idFilm) {
        KeyHolder keyHolder= new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id_film", idFilm);
        namedParameters.addValue("id_participant", idParticipant);

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);
        jdbcTemplate.update(INSERT, namedParameters);
    }
}
