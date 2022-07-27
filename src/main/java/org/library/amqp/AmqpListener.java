package org.library.amqp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.dto.AuthorDto;
import org.library.model.Author;
import org.library.service.AuthorService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class AmqpListener implements ChannelAwareMessageListener {

    private final AuthorService authorService;

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message.getBody());

        String name = jsonNode.get("name").textValue();
        String info = jsonNode.get("info").toString();

        Author author = authorService.getByName(name);

        if (author == null) {
            log.info("author with name \"" + name + "\" not found");
            try {
                author = new Author();
                author.setName(name);
                author.setInfo(info);
                authorService.save(author);
                log.info("author is recorded in the database");
            } catch (Exception e) {
                log.info("failed to record the author");
                throw new IOException("failed to record the author");
            }
        } else {
            log.info("author with the name \"" + name + "\" is already in the database");
        }
    }
}