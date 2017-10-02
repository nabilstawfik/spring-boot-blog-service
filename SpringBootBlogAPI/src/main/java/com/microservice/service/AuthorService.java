/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.model.Author;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface AuthorService {

    /**
     * Save Author object to database.
     * 
     * @param author
     * @return author 
     */
    public Author save(Author author);

    /**
     * Find all authors order by nickName ASC
     * @return List of Author
     */
    List<Author> findAll();
    
}
