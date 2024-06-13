package com.mcrs.personsInput.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OldPersonRequestDto {

    private String name;
    private String dni;
    private String email;

}
