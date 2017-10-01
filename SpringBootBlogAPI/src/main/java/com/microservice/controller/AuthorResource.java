/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.controller;

import com.microservice.dto.AuthorDto;
import com.microservice.model.Author;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservice.service.AuthorService;
import com.microservice.transformer.AuthorTransformer;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping(path = "/api/authors")
public class AuthorResource {

    private static final Logger LOGGER = Logger.getLogger(AuthorResource.class.getName());

    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorTransformer authorTransformer;
     /**
     * Add new author.
     * <p>
     * <b>Service URL:</b> /api/authors</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * var authorDto = {id: [long], nickName: [string]};
     * <br/>
     * $.ajax({ type: "POST", url: "/api/authors", contentType:"application/json", 
     * data: JSON.stringify(authorDto),
     * beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Basic XXXXXXXXXX');},
     * success: function(data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Sample Response:</b> { id : [long], nickName: [string], links:{}}</p>
     *
     * @param authorDto Json object.
     * @return authorDto Json object.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        try {
            authorDto = authorTransformer.toDto(authorService.save(authorTransformer.toModel(authorDto)));
            authorDto.add(linkTo(methodOn(AuthorResource.class).findAll()).withSelfRel());
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
     /**
     * List authors ordered by nickName ASC.
     * <p>
     * <b>Service URL:</b> /api/authors</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * $.ajax({ type: "GET", url: "/api/authors", contentType:
     * beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Basic XXXXXXXXXX');},
     * success: function(data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Sample Response:</b> [{ id : [long], nickName: [string], links:{}},.....]</p>
     *
     * @return List of authorDto Json object.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<AuthorDto>> findAll() {
        try {
            List<Author> authorsList = authorService.findAll();
            List<AuthorDto> authorsDtoList = authorsList.stream().map(author -> authorTransformer.toDto(author)).collect(Collectors.toList());
            return new ResponseEntity<>(authorsDtoList, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
