package com.mcrs.personsInput.person.repository;

import com.mcrs.personsInput.person.model.NewPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewPersonRepository extends MongoRepository<NewPerson, String> {

    Optional<NewPerson> findByDni(String dni);

    List<NewPerson> findByName(String name);
}
