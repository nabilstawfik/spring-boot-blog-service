/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.controller;

import com.microservice.dto.BlogDto;
import com.microservice.exception.AuthorNotFoundException;
import com.microservice.model.Blog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.service.BlogService;
import com.microservice.transformer.BlogTransformer;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
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
    @Autowired
    BlogTransformer blogTransformer;
     /**
     * Add new blog.
     * <p>
     * <b>Service URL:</b> /api/blogs</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * var blogDto = {id: [long], title: [string], body: [string], creationTime: [datetime], author: {id: [long], nickName: [string]}};
     * <br/>
     * $.ajax({ type: "POST", url: "/api/blogs", contentType:"application/json", 
     * data: JSON.stringify(blogDto),
     * beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Basic XXXXXXXXXX');},
     * success: function(data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Sample Response:</b> {id: [long], title: [string], body: [string], creationTime: [datetime], author: {id: [long], nickName: [string]}, links:{}}</p>
     *
     * @param blogDto Json object.
     * @return blogDto Json object.
     * @throws com.microservice.exception.AuthorNotFoundException
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto) throws AuthorNotFoundException {
        blogDto.setCreationTime(Calendar.getInstance().getTime());
        blogDto = blogTransformer.toDto(blogService.save(blogTransformer.toModel(blogDto)));
        blogDto.add(linkTo(methodOn(BlogResource.class).update(blogDto)).withSelfRel());
        blogDto.add(linkTo(methodOn(BlogResource.class).findAll(0, 20)).withSelfRel());
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }
     /**
     * Update an existing blog.
     * <p>
     * <b>Service URL:</b> /api/blogs</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * var blogDto = {id: [long], title: [string], body: [string], creationTime: [datetime], author: {id: [long], nickName: [string]}};
     * <br/>
     * $.ajax({ type: "PUT", url: "/api/blogs", contentType:"application/json", 
     * data: JSON.stringify(blogDto),
     * beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Basic XXXXXXXXXX');},
     * success: function(data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Sample Response:</b> {id: [long], title: [string], body: [string], creationTime: [datetime], author: {id: [long], nickName: [string]}, links:{}}</p>
     *
     * @param blogDto Json object.
     * @return blogDto Json object.
     * @throws com.microservice.exception.AuthorNotFoundException
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<BlogDto> update(@RequestBody BlogDto blogDto) throws AuthorNotFoundException {
        blogDto = blogTransformer.toDto(blogService.save(blogTransformer.toModel(blogDto)));
        blogDto.add(linkTo(methodOn(BlogResource.class).findAll(0, 20)).withSelfRel());
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
    }
     /**
     * List blogs ordered by creationtime DESC.
     * <p>
     * <b>Service URL:</b> /api/blogs</p>
     * <p>
     * <b>Sample call:</b></p>
     * <p>
     * $.ajax({ type: "GET", url: "/api/blogs?page=0&pageSize=20",
     * beforeSend: function(xhr){xhr.setRequestHeader('Authorization', 'Basic XXXXXXXXXX');},
     * success: function(data){}, error: function (XMLHttpRequest, textStatus, errorThrown) {}
     * });</p>
     * <p>
     * <b>Success Response:</b> Code = 200</p>
     * <p>
     * <b>Sample Response:</b> [{id: [long], title: [string], body: [string], creationTime: [datetime], author: {id: [long], nickName: [string]}}, links:{},....]</p>
     *
     * @param page
     * @param pageSize     
     * @return List of blogDto Json object.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<BlogDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize) {

        List<Blog> blogsList = blogService.findAll(page, pageSize);
        List<BlogDto> blogsDtoList = blogsList.stream().map(blog -> {
            BlogDto blogDto = blogTransformer.toDto(blog);
            try {
                blogDto.add(linkTo(methodOn(BlogResource.class).update(blogDto)).withSelfRel());
            } catch (AuthorNotFoundException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return blogDto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(blogsDtoList, HttpStatus.OK);
    }
}
