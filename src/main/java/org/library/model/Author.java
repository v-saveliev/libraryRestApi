package org.library.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Author extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Type(type = "jsonb")
    @Column(name = "info")
    private JsonNode info;

    @ManyToMany(mappedBy = "bookAuthors")
    private List<Book> authorBooks;

    @Override
    public String toString() {
        return "Author name='" + name;
    }

    public void addBookToList(Book book) {
        if (authorBooks == null) authorBooks = new ArrayList<>();
        authorBooks.add(book);
    }
}
