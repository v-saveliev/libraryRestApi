package org.library.amqp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.dto.AuthorDto;
import org.library.dto.AuthorMapperDto;
import org.library.model.Author;
import org.library.service.AuthorService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class AmqpListener  {

    private final AuthorService authorService;
    private final AuthorMapperDto authorMapper;

    @RabbitListener(queues = "authors")
    public void onMessage(AuthorDto authorDto, Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {

          String name = authorDto.getName();

          Author author = authorService.getByName(name);
          if (author == null) {
              log.info("author with name \"" + name + "\" not found");
              try {
                  author = authorMapper.convertAuthorDtoToEntity(authorDto);
                  authorService.save(author);
                  log.info("author is recorded in the database");

                  channel.basicAck(tag, false);
              } catch (Exception e) {
                  log.info("failed to record the author");
                  channel.basicReject(tag, true);
              }
          } else {
              log.info("author with the name \"" + name + "\" is already in the database");
          }
    }
}