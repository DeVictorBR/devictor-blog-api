package tech.devictor.devictor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.devictor.devictor.exceptions.dtos.InvalidParamDto;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(DevictorException.class)
    public ProblemDetail handleDevictorException(DevictorException ex) {
        return ex.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var invalidParams = ex.getFieldErrors()
                .stream()
                .map(fe -> new InvalidParamDto(fe.getField(), fe.getDefaultMessage()))
                .toList();
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Invalid request parameters");
        pd.setDetail("There is invalid fields on the request");
        pd.setProperty("invalid-params", invalidParams);
        return pd;
    }
}
