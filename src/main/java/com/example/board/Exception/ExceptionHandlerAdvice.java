package com.example.board.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
//ControllerAdvice는 AOP를 적용한 것으로서, 모든 @Controller에 대한, 전역적으로 발생할 수 있는 예외를 잡아서 처리
@ControllerAdvice
public class ExceptionHandlerAdvice {
//    version1
//    @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object>  notFound() {
//        return new ResponseEntity<Object>(
//                "no entity", new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }

//    version2 : 객체를 통한 json리턴
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound(EntityNotFoundException e) {
        String content =
            "<header>"
                    + "<h1><span>존재하지 않는 내용의 화면입니다.</span>" + "</h1>"
                    + "</header>";
//        final ErrorResponse response = new ErrorResponse
//                (LocalDateTime.now(),HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<String>(content, HttpStatus.NOT_FOUND);
    }

//    version3 : customizing 예외처리
    @ExceptionHandler(NoListException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(NoListException e) {
        final ErrorResponse response = new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}