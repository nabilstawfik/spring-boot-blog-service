/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.integration;

import com.microservice.repository.AuthorRepository;
import com.microservice.AbstractBaseTest;
import com.microservice.model.Author;
import com.microservice.service.AuthorService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author nabil
 */
public class AuthorServiceImplIntegrationTest extends AbstractBaseTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;

    @Test
    public void saveTest() {
        Author actual = authorService.save(new Author("Nabil"));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);
    }

    @Test
    public void updateAuthorNickNameTest() {
        Author actual = authorService.save(new Author("Nabil Tawfik"));
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);

        actual.setNickName("Nabil Saad Tawfik");
        Author actualUpdated = authorService.save(actual);

        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getNickName()).isEqualTo("Nabil Saad Tawfik");
        assertThat(actualUpdated.getId()).isEqualTo(actual.getId());
    }

    @Test
    public void findAllTest() {
        authorRepository.deleteAll();
        
        authorService.save(new Author("Tom"));
        authorService.save(new Author("Andre"));
        authorService.save(new Author("Nabil"));
        List<Author> actuals = authorService.findAll();
        
        assertThat(actuals).isNotNull();
        assertThat(actuals.get(0).getNickName()).isEqualTo("Andre");
        assertThat(actuals.get(1).getNickName()).isEqualTo("Nabil");
        assertThat(actuals.get(2).getNickName()).isEqualTo("Tom");
    }

}
