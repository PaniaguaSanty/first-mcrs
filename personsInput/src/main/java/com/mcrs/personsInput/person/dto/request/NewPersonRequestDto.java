package com.mcrs.personsInput.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPersonRequestDto {

    private String name;
    private String dni;
    private String email;

}
