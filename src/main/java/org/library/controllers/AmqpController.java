package org.library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.library.model.Author;
import org.library.service.AuthorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AmqpController {

    private AuthorService authorService;

    @Autowired
    public AmqpController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RabbitListener(queues = "authors")
    public void listenAuthors(String name) {
        Author author = authorService.getByName(name);

        if (author == null) {
            log.info("author with name \"" + name + "\" not found");
            try {
                author = new Author();
                author.setName(name);
                authorService.save(author);
                log.info("author is recorded in the database");
            } catch (Exception e) {
                log.info("failed to record the author");
            }
        } else {
            log.info("author with the name \"" + name + "\" is already in the database");
        }
    }
}
