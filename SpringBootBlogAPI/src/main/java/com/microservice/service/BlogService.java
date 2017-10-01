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

    List<Blog> findAll(int page, int pageSize);

    Blog save(Blog blog) throws AuthorNotFoundException;

}
