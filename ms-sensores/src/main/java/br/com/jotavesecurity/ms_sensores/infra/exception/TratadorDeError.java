package br.com.jotavesecurity.ms_sensores.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TratadorDeError {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErroValidacao(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getAllErrors().stream().forEach((error) ->{
            String campo = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(campo, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "Timestamp", LocalDateTime.now(),
                "Status", HttpStatus.BAD_REQUEST,
                "Erro", "Dados Inválidos",
                "Detalhes", errors
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tratarErroGenérico(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "Timestamp", LocalDateTime.now(),
                "Status", HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro", "Erro interno no servidor",
                "Detalhes", ex.getMessage()
        ));
    }

}
