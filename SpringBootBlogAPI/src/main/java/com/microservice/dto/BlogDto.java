/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nabil
 */
@Getter
@Setter
public class BlogDto extends AbstractDto {

    @JsonProperty(value = "id")
    private long blogId;
    @NotNull
    private String title;
    @NotNull
    private String body;
    private Date creationTime;
    @JsonProperty(value = "author", required = true)
    private AuthorDto authorDto;

    public BlogDto() {
    }

    public BlogDto(long blogId, String title, String body, Date creationTime, AuthorDto authorDto) {
        this.blogId = blogId;
        this.title = title;
        this.body = body;
        this.creationTime = creationTime;
        this.authorDto = authorDto;
    }

    public BlogDto(String title, String body, Date creationTime, AuthorDto authorDto) {
        this.title = title;
        this.body = body;
        this.creationTime = creationTime;
        this.authorDto = authorDto;
    }

}
