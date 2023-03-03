package com.example.board.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorPostForm {


    private String name;
//    advance단계
    private String email;
    private String password;
    private String role;
}
