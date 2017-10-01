/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.integration;

import com.microservice.exception.AuthorNotFoundException;
import com.microservice.repository.BlogRepository;
import com.microservice.AbstractBaseTest;
import com.microservice.model.Author;
import com.microservice.model.Blog;
import com.microservice.service.AuthorService;
import com.microservice.service.BlogService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author nabil
 */
public class BlogServiceImplIntegrationTest extends AbstractBaseTest {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorService;

    @Test
    public void saveTest() throws AuthorNotFoundException {
        Author author = authorService.save(new Author("Nabil"));
        Blog blog = new Blog("Title1", "Body1", Date.valueOf(LocalDate.now()), author);
        blog = blogService.save(blog);

        assertThat(blog).isNotNull();
        assertThat(blog.getId()).isNotEqualTo(0);
    }

    @Test
    public void updateBlogTitleTest() throws AuthorNotFoundException {
        Author author = authorService.save(new Author("Nabil"));
        Blog actual = blogService.save(new Blog("Title1", "Body1", Date.valueOf(LocalDate.now()), author));

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);

        actual.setTitle("Updated Title");
        Blog actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getTitle()).isEqualTo("Updated Title");
        assertThat(actualUpdated.getId()).isEqualTo(actual.getId());

    }
    
    @Test
    public void updateBlogBodyTest() throws AuthorNotFoundException {
        Author author = authorService.save(new Author("Nabil"));
        Blog actual = blogService.save(new Blog("Title1", "Body1", Date.valueOf(LocalDate.now()), author));

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);

        actual.setBody("Updated Body");
        Blog actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getBody()).isEqualTo("Updated Body");
        assertThat(actualUpdated.getId()).isEqualTo(actual.getId());

    }

    @Test
    public void updateBlogTitleAndBodyTest() throws AuthorNotFoundException {
        Author author = authorService.save(new Author("Nabil"));
        Blog actual = blogService.save(new Blog("Title1", "Body1", Date.valueOf(LocalDate.now()), author));

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotEqualTo(0);

        actual.setTitle("Updated Title");
        actual.setBody("Updated Body");
        Blog actualUpdated = blogService.save(actual);
        
        assertThat(actualUpdated).isNotNull();
        assertThat(actualUpdated.getTitle()).isEqualTo("Updated Title");
        assertThat(actualUpdated.getBody()).isEqualTo("Updated Body");
        assertThat(actualUpdated.getId()).isEqualTo(actual.getId());

    }
    
    @Test
    public void findAllByOrderByCreationTimeDescTest() throws AuthorNotFoundException {
        blogRepository.deleteAll();
        Author author = authorService.save(new Author("Nabil"));
        Blog blog1 = new Blog("Title1", "Body1", Date.valueOf(LocalDate.now()), author);
        Blog blog2 = new Blog("Title2", "Body2", Date.valueOf(LocalDate.now().plusDays(1)), author);
        blogService.save(blog1);
        blogService.save(blog2);

        List<Blog> actualBlogList = blogService.findAll(0, 20);
        assertThat(actualBlogList).isNotNull();
        assertThat(actualBlogList.get(0).getTitle()).isEqualTo("Title2");
        assertThat(actualBlogList.get(1).getTitle()).isEqualTo("Title1");
    }
}
