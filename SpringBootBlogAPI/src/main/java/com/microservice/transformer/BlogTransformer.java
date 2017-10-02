/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.transformer;

import com.microservice.dto.BlogDto;
import com.microservice.exception.AuthorNotFoundException;
import com.microservice.model.Author;
import com.microservice.model.Blog;
import com.microservice.repository.AuthorRepository;
import com.microservice.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nabil
 */
@Component
public class BlogTransformer {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorTransformer authorTransformer;

    /**
     * Transform Blog to BlogDto
     * 
     * @param blog
     * @return BlogDto
     */
    public BlogDto toDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(blog.getId());
        blogDto.setBody(blog.getBody());
        blogDto.setTitle(blog.getTitle());
        blogDto.setCreationTime(blog.getCreationTime());
        blogDto.setAuthorDto(authorTransformer.toDto(blog.getAuthor()));
        return blogDto;
    }

    /**
     * Transform BlogDto to Blog
     * 
     * @param blogDto
     * @return Blog
     * @throws AuthorNotFoundException
     */
    public Blog toModel(BlogDto blogDto) throws AuthorNotFoundException {
        Author author;
        Blog blog;
        if (blogDto.getBlogId() == 0) {
            author = authorRepository.findOne(blogDto.getAuthorDto().getAuthorId());
            if (author == null) {
                throw new AuthorNotFoundException();
            }
            blog = new Blog(blogDto.getTitle(), blogDto.getBody(), blogDto.getCreationTime(), author);
        } else {
            blog = blogRepository.findOne(blogDto.getBlogId());
            blog.setTitle(blogDto.getTitle());
            blog.setBody(blogDto.getBody());
        }
        return blog;
    }
}
