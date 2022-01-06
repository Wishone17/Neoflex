package ru.neoflex.coffetime.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.neoflex.coffetime.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PersonDAO() {
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON(name, surname, email) VALUES (?, ?, ?)", person.getName(), person.getSurname(), person.getEmail());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", id);
    }

    public Person showPerson(int id){
            return jdbcTemplate.query("SELECT * FROM PERSON WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                    .stream().findAny().orElse(null);
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, email=? WHERE id=?", updatePerson.getName(), updatePerson.getSurname(), updatePerson.getEmail(), id);
    }

    public List<String> getEmail(){
        return jdbcTemplate.queryForList("SELECT email FROM PERSON", String.class);
    }
}




