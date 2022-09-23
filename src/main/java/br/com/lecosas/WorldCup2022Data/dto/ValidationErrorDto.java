package br.com.lecosas.WorldCup2022Data.dto;

import lombok.Getter;

@Getter
public class ValidationErrorDto {
    
    private String field;
    private String error;

    public ValidationErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }

}
