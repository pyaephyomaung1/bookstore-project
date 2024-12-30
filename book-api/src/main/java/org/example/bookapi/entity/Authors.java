package org.example.bookapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;
    private String nationality;
    private LocalDate birthDate;

    @Column(columnDefinition = "TEXT")
    private String biography;

    private String authorProfileUrl;
    private String authorCode;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Books> books = new ArrayList<>();


    public Authors() {}

    public Authors(String name, String nationality, LocalDate birthDate, String biography, String authorProfileUrl, String authorCode) {
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
        this.authorProfileUrl = authorProfileUrl;
        this.authorCode = authorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAuthorCode(){
        return authorCode;
    }

    public void setAuthorCode(String authorCode){
        this.authorCode = authorCode;
    }

    public String getAuthorProfileUrl() {
        return authorProfileUrl;
    }

    public void setAuthorProfileUrl(String authorProfileUrl) {
        this.authorProfileUrl = authorProfileUrl;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }


}
