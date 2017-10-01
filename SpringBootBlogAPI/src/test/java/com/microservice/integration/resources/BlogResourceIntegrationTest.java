package com.microservice.integration.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabil
 */
import com.microservice.AbstractRestBaseTest;
import com.microservice.dto.AuthorDto;
import com.microservice.dto.BlogDto;
import com.microservice.exception.AuthorNotFoundException;
import com.microservice.model.Author;
import com.microservice.service.AuthorService;
import com.microservice.service.BlogService;
import com.microservice.transformer.AuthorTransformer;
import com.microservice.transformer.BlogTransformer;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class BlogResourceIntegrationTest extends AbstractRestBaseTest {

    private static final String INSTANCE_SERVICE_END_POINT_URL = "/api/blogs";

    AuthorDto authorDto;

    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorService;
    @Autowired
    BlogTransformer blogTransformer;
    @Autowired
    AuthorTransformer authorTransformer;

    @Before
    public void setup() {
        authorDto = authorTransformer.toDto(authorService.save(new Author("Author Name")));
    }

    @Test
    public void saveTest() {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actual = this.restTemplate.postForEntity(INSTANCE_SERVICE_END_POINT_URL, httpEntity, BlogDto.class);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody().getBlogId()).isNotEqualTo(0);
    }

    @Test
    public void updateTitleTest() throws AuthorNotFoundException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogTransformer.toDto(blogService.save(blogTransformer.toModel(blogDto)));

        blogDto.setTitle("Updated Title");
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL, HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getTitle()).isEqualTo("Updated Title");
    }

    @Test
    public void updateBodyTest() throws AuthorNotFoundException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogTransformer.toDto(blogService.save(blogTransformer.toModel(blogDto)));

        blogDto.setBody("Updated Body");
        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL, HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getBody()).isEqualTo("Updated Body");
    }

    @Test
    public void updateTitleAndBodyTest() throws AuthorNotFoundException {
        BlogDto blogDto = new BlogDto("Title", "Body", Date.valueOf(LocalDate.now()), authorDto);
        blogDto = blogTransformer.toDto(blogService.save(blogTransformer.toModel(blogDto)));

        blogDto.setTitle("Updated Title");
        blogDto.setBody("Updated Body");

        HttpEntity httpEntity = new HttpEntity(blogDto, headers);
        ResponseEntity<BlogDto> actualResponse = this.restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL, HttpMethod.PUT, httpEntity, BlogDto.class);

        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualResponse.getBody().getBlogId()).isEqualTo(blogDto.getBlogId());
        assertThat(actualResponse.getBody().getTitle()).isEqualTo("Updated Title");
        assertThat(actualResponse.getBody().getBody()).isEqualTo("Updated Body");
    }

    @Test
    public void findAllTest() {
        Map<String, Integer> params = new HashMap();
        params.put("page", 0);
        params.put("pageSize", 100);
        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity<BlogDto[]> actualResponse = restTemplate.exchange(INSTANCE_SERVICE_END_POINT_URL, HttpMethod.GET, httpEntity, BlogDto[].class, params);
        List<BlogDto> actualList = Arrays.asList(actualResponse.getBody());

        assertThat(actualList).isNotNull();
    }

}
