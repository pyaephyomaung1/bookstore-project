package com.example.biblo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private int id;
    private String name;
    private String nationality;
    private String birthDate;
    private String biography;
    private String authorProfile;
    private List<BookDTO> books;

    public AuthorDTO(int id, String name, String nationality, String birthDate,
            String biography, String authorProfile, List<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
        this.authorProfile = authorProfile;
        this.books = books;
    }
}
