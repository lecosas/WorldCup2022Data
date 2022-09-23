package br.com.lecosas.WorldCup2022Data.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lecosas.WorldCup2022Data.dto.ValidationErrorDto;

@RestControllerAdvice
public class CustomErrorValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorDto> handle(MethodArgumentNotValidException exception) {
        List<ValidationErrorDto> dtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationErrorDto erro = new ValidationErrorDto(e.getField(), message);
            dtos.add(erro);
        });

        return dtos;
    }

}
