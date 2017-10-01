/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service.impl;

import com.microservice.exception.AuthorNotFoundException;
import com.microservice.model.Blog;
import com.microservice.repository.BlogRepository;
import com.microservice.service.BlogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author nabil
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    
    @Override
    public Blog save(Blog blog) throws AuthorNotFoundException {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findAll(int page, int pageSize) {
        return blogRepository.findAll(new PageRequest(page, pageSize, Sort.Direction.DESC, "creationTime")).getContent();
    }

}
