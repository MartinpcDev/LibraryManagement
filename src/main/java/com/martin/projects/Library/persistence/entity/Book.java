package com.martin.projects.Library.persistence.entity;

import com.martin.projects.Library.util.BookGender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, unique = true)
  private String isbn;

  @Column(nullable = false)
  private Integer publicationYear;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BookGender gender;

  @Column(nullable = false)
  private Integer stock;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @ManyToOne
  @JoinColumn(name = "editorial_id", nullable = false)
  private Editorial editorial;
}
