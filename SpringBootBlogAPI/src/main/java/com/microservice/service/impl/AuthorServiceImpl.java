/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.repository.AuthorRepository;
import com.microservice.service.AuthorService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Sort;

/**
 *
 * @author nabil
 */
@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
        
    }

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepository.findAll(new Sort(Sort.Direction.ASC, "nickName")).spliterator(), false).collect(Collectors.toList());
    }

}
