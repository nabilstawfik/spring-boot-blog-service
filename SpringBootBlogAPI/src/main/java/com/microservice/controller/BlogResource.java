/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.controller;

import com.microservice.dto.BlogDto;
import com.microservice.exception.AuthorNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.service.BlogService;
import java.util.Calendar;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping(path = "/api/blogs")
public class BlogResource {

    private static final Logger LOGGER = Logger.getLogger(BlogResource.class.getName());

    @Autowired
    BlogService blogService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto) throws AuthorNotFoundException {
        blogDto.setCreationTime(Calendar.getInstance().getTime());
        blogDto = blogService.save(blogDto);
        blogDto.add(linkTo(methodOn(BlogResource.class).update(blogDto)).withSelfRel());
        blogDto.add(linkTo(methodOn(BlogResource.class).findAll(0, 20)).withSelfRel());
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> update(@RequestBody BlogDto blogDto) throws AuthorNotFoundException {
        blogDto = blogService.save(blogDto);
        blogDto.add(linkTo(methodOn(BlogResource.class).findAll(0, 20)).withSelfRel());
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<BlogDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
            List<BlogDto> blogsList = blogService.findAll(page, pageSize);
            return new ResponseEntity<>(blogsList, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
