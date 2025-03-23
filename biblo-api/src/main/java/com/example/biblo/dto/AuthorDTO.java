package com.example.biblo.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private int id;
    private String name;
    private String nationality;
    private String birthDate;
    private String biography;
    private String authorProfile;
    private List<BookDTO> books;
}
