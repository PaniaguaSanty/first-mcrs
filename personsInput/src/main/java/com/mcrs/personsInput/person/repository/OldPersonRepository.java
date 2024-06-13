package com.mcrs.personsInput.person.repository;

import com.mcrs.personsInput.person.model.OldPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OldPersonRepository extends JpaRepository<OldPerson, Long> {

    Optional<OldPerson> findByDni(String dni);

}
