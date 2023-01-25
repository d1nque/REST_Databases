package com.d1nque.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "title", length = 60)
    @JsonProperty("title")
    private String title;

    @Column(name = "author")
    @JsonProperty("author")
    private String author;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonProperty("category")
    private Category category;

    public Book(Long id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category.getCategoryName() +
                '}';
    }

}
