package danekerscode.exception.handler;

import danekerscode.exception.Base64OperationException;
import danekerscode.exception.EmailRegisteredException;
import danekerscode.exception.LinkExpiredException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.function.BiFunction;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(Base64OperationException.class)
    ProblemDetail handle(Base64OperationException e) {
        return withDetails.apply(500, e);
    }

    @ExceptionHandler(EmailRegisteredException.class)
    ProblemDetail handle(EmailRegisteredException e) {
        return withDetails.apply(400, e);
    }

    @ExceptionHandler(LinkExpiredException.class)
    ProblemDetail handle(LinkExpiredException e) {
        return withDetails.apply(404, e);
    }

    private final BiFunction<Integer, RuntimeException, ProblemDetail>
            withDetails = (code, ex) -> ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(code), ex.getMessage());
}
