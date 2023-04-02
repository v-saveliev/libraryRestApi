package org.library.repository;

import org.library.model.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select authors from Author authors where authors.name in :names")
    List<Author> getAllByNames(Pageable pageable, @Param("names") Collection<String> authorsNames);
}
