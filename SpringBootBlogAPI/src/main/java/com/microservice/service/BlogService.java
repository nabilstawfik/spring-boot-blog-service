/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.exception.AuthorNotFoundException;
import com.microservice.model.Blog;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface BlogService {

    /**
     * Find all blogs order by creation time DESC
     * 
     * @param page
     * @param pageSize
     * @return List of Blog
     */
    List<Blog> findAll(int page, int pageSize);

    /**
     * Save blog object to database.
     * 
     * @param blog
     * @return blog
     * @throws AuthorNotFoundException
     */
    Blog save(Blog blog) throws AuthorNotFoundException;

}
