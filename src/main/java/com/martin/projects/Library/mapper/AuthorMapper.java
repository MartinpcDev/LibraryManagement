package com.martin.projects.Library.mapper;

import com.martin.projects.Library.dto.request.SaveAuthor;
import com.martin.projects.Library.dto.response.AuthorDto;
import com.martin.projects.Library.persistence.entity.Author;
import java.util.List;

public class AuthorMapper {

  public static AuthorDto toAuthorDto(Author author) {
    if (author == null) {
      return null;
    }

    return new AuthorDto(
        author.getId(),
        author.getName(),
        author.getLastname(),
        author.getBirthdate(),
        author.getNationality()
    );
  }

  public static List<AuthorDto> toAuthorDtoList(List<Author> authors) {
    if (authors == null) {
      return null;
    }

    return authors.stream()
        .map(AuthorMapper::toAuthorDto)
        .toList();
  }

  public static Author toAuthorEntity(SaveAuthor authorDto) {
    if (authorDto == null) {
      return null;
    }

    Author author = new Author();
    author.setName(authorDto.getName());
    author.setLastname(authorDto.getLastname());
    author.setBirthdate(authorDto.getBirthdate());
    author.setNationality(authorDto.getNationality());

    return author;
  }

  public static void updateAuthorEntity(Author oldAuthor, SaveAuthor authorDto) {
    if (oldAuthor == null || authorDto == null) {
      return;
    }

    oldAuthor.setName(authorDto.getName());
    oldAuthor.setLastname(authorDto.getLastname());
    oldAuthor.setBirthdate(authorDto.getBirthdate());
    oldAuthor.setNationality(authorDto.getNationality());
  }
}
