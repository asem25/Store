package ru.semavin.app.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.semavin.app.model.Person;
import ru.semavin.app.repositories.PeopleRepository;
import ru.semavin.app.security.PersonDetails;

import java.util.Optional;
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;
@Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
