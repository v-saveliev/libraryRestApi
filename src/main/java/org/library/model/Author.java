package org.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "bookAuthors")
    private List<Book> authorBooks;

    @Override
    public String toString() {
        return "Author name='" + name;
    }
}
