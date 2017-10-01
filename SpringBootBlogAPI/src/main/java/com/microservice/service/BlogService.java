/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.service;

import com.microservice.dto.BlogDto;
import com.microservice.exception.AuthorNotFoundException;
import java.util.List;

/**
 *
 * @author nabil
 */
public interface BlogService {

    List<BlogDto> findAll(int page, int pageSize);

    BlogDto save(BlogDto blogDto) throws AuthorNotFoundException;

}
