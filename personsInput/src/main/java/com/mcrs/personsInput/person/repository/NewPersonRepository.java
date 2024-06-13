package com.mcrs.personsInput.person.repository;

import com.mcrs.personsInput.person.model.PersonNew;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewPersonRepository extends MongoRepository<PersonNew, String> {

    Optional<PersonNew> findByDni(String dni);

    List<PersonNew> findByName(String name);
}
