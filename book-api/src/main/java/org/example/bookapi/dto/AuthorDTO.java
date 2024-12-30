package org.example.bookapi.dto;




import java.time.LocalDate;
import java.util.List;

public class AuthorDTO {
        private String name;
        private String nationality;
        private LocalDate birthDate;
        private String biography;
        private String authorProfileUrl;
        private String authorCode;
        private List<BookDTO> books;
        public AuthorDTO() {}

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

    public String getAuthorProfileUrl() {
        return authorProfileUrl;
    }

    public void setAuthorProfileUrl(String authorProfileUrl) {
        this.authorProfileUrl = authorProfileUrl;
    }

    public String getAuthorCode() {
            return authorCode;
    }

    public void setAuthorCode(String authorCode) {
            this.authorCode = authorCode;
    }



   public List<BookDTO> getBooks() {
            return books;
   }

   public void setBooks(List<BookDTO> books) {
            this.books = books;
   }




    public AuthorDTO(String name, String nationality, LocalDate birthDate, String biography, String authorProfileUrl, String authorCode ,List<BookDTO> books) {
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
        this.authorProfileUrl = authorProfileUrl;
        this.authorCode = authorCode;
        this.books = books;


    }

}
