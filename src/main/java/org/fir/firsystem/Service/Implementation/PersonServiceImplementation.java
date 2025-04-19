package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.Model.Person;
import org.fir.firsystem.Repository.PersonRepository;
import org.fir.firsystem.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImplementation implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
