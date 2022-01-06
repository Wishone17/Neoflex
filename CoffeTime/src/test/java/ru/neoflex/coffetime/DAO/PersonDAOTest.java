package ru.neoflex.coffetime.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.neoflex.coffetime.models.Person;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonDAOTest {

    @InjectMocks
    private PersonDAO personDAO;

    @Mock
    JdbcTemplate jdbcTemplate;

    ArrayList<Person> people;
    ArrayList<String> emailList;

    @BeforeEach
    void addList(){
        this.people = new ArrayList<>();
        this.people.add(new Person(1, "Илья", "Макаров", "makar@gmail.com"));
        this.people.add(new Person(2, "Игорь", "Ульянов", "ulig98@gmail.com"));
        this.people.add(new Person(3, "Владимир", "Васильев", "vasiliev@gmail.com"));

        this.emailList = new ArrayList<>();
        this.emailList.add("makar@gmail.com");
        this.emailList.add("ulig98@gmail.com");
        this.emailList.add("vasiliev@gmail.com");
    }


    @Test
    void getPeople() {
        when(jdbcTemplate.query(anyString(),(BeanPropertyRowMapper) anyObject())).thenReturn(people);
                assertEquals(people.size(), personDAO.getPeople().size());
    }

    @Test
    void save() {
        Person upPerson = new Person(4, "Иван", "Валов", "val@gmail.com");
        personDAO.save(upPerson);
        verify(jdbcTemplate).update(anyString(), eq(upPerson.getName()), eq(upPerson.getSurname()), eq(upPerson.getEmail()));

    }

    @Test
    void delete() {
        personDAO.delete(0);
        verify(jdbcTemplate).update(anyString(), eq(0));
    }


    @Test
    void showPerson() {
        when(jdbcTemplate.query(anyString(), (Object[]) anyObject(), (BeanPropertyRowMapper) anyObject())).thenReturn(people);
        assertEquals(people.get(0), personDAO.showPerson(0));
    }

    @Test
    void update() {
        Person upPerson = new Person(1, "Илья", "Макаров", "makar@gmail.com");
        personDAO.update(0, upPerson);
        verify(jdbcTemplate).update(anyString(), eq(upPerson.getName()), eq(upPerson.getSurname()), eq(upPerson.getEmail()), eq(0));
    }
    @Test
    void getEmail() {
        when(jdbcTemplate.queryForList("SELECT email FROM PERSON", String.class)).thenReturn(emailList);
            assertEquals(emailList.size(), personDAO.getEmail().size());
    }

}