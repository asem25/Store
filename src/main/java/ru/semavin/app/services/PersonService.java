package ru.semavin.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.semavin.app.dto.PersonDTO;
import ru.semavin.app.dto.PersonProfileDTO;
import ru.semavin.app.model.Person;
import ru.semavin.app.repositories.PeopleRepository;
import ru.semavin.app.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public PersonService(PeopleRepository peopleRepository, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Person> findById(Integer id){
        return peopleRepository.findById(id);
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public void save(Person person){
        peopleRepository.save(person);
    }
    public void edit(Integer id, Person person){
        person.setId(id);
        peopleRepository.save(person);
    }
    public void delete(Integer id){
        peopleRepository.deleteById(id);
    }
    public Optional<Person> findByName(Person person){
        return peopleRepository.findByUsername(person.getUsername());
    }
    public PersonProfileDTO getCurrentUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Person person = peopleRepository.findByUsername(userName)
                .orElseThrow(() -> new PersonNotFoundException("User not found with username: " + userName));

        return convertToPersonDTO(person);
    }
    private PersonProfileDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonProfileDTO.class);
    }
}
