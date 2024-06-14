package com.mcrs.personsInput.person.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "New_Persons")
public class PersonNew {

    @Id
    private String id;

    private String name;
    private String dni;
    private String email;
}
