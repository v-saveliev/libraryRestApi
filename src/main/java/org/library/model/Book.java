package org.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "pages")
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> bookAuthors;

    @Override
    public String toString() {
        return "Book{" +
                "id='"+ getId() +
                ", title='" + title + '\'' +
                ", pages=" + pages + ", authors: "
                + bookAuthors.toString() + "}";
    }
}
