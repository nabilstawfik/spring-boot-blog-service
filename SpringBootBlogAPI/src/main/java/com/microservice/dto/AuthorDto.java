/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author nabil
 */
@Getter
@Setter
public class AuthorDto extends AbstractDto {

    @JsonProperty(value = "id")
    private long authorId;
    @NotNull
    private String nickName;

    public AuthorDto() {
    }

    public AuthorDto(long authorId, String nickName) {
        this.authorId = authorId;
        this.nickName = nickName;
    }

    public AuthorDto(String nickName) {
        this.nickName = nickName;
    }

}
