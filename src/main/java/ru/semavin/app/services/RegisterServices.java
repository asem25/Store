package ru.semavin.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semavin.app.model.Person;
import ru.semavin.app.repositories.PeopleRepository;

import java.util.Optional;

@Service
//TODO Добавить валидацию
public class RegisterServices {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegisterServices(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Person person){
        person.setPass(passwordEncoder.encode(person.getPass()));
        person.setRole("ROLE_USER");
        personService.save(person);
    }
    @Transactional
    public Optional<Person> loadUser(Person person){
        return personService.findByName(person);
    }

}
