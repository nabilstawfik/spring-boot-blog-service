/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.transformer;

import com.microservice.dto.AuthorDto;
import com.microservice.model.Author;
import com.microservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nabil
 */
@Component
public class AuthorTransformer {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Transform Author to AuthorDto
     * @param author
     * @return authorDto
     */
    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getNickName());
    }

    /**
     * Transform the AuthorDto to Author.
     * @param authorDto
     * @return author
     */
    public Author toModel(AuthorDto authorDto) {
        Author author;
        if (authorDto.getAuthorId() != 0) {
            author = authorRepository.findOne(authorDto.getAuthorId());
        } else {
            author = new Author(authorDto.getNickName());
        }
        return author;
    }

}
