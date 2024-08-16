package com.apiexample_1.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class RegistrationDto {

    private Long id;


    private String name;


    private String email;

    private String mobile;
     private String massage;

}