package br.com.sisnema.milionarios.controllers.exceptions;

import br.com.sisnema.milionarios.service.exceptions.BancoDeDadosException;
import br.com.sisnema.milionarios.service.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

// Para interceptar exceções
@ControllerAdvice
public class ManipuladorDeExcecoes {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(EntidadeNaoEncontradaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(Instant.now()); // Horário atual
        erroPadrao.setStatus(status.value());
        erroPadrao.setErro("Recurso não encontrado!");
        erroPadrao.setMsg(e.getMessage());
        erroPadrao.setCaminho(request.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<ErroPadrao> bancodedados(BancoDeDadosException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setTimestamp(Instant.now()); // Horário atual
        erroPadrao.setStatus(status.value());
        erroPadrao.setErro("Banco de dados Exception!");
        erroPadrao.setMsg(e.getMessage());
        erroPadrao.setCaminho(request.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }
}
