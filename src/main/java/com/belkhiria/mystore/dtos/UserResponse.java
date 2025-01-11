package com.belkhiria.mystore.dtos;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}

