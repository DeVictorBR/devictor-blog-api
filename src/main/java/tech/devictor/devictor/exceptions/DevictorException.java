package tech.devictor.devictor.exceptions;

import org.springframework.http.ProblemDetail;

public abstract class DevictorException extends RuntimeException {

    public DevictorException(String detail) {
        super(detail);
    }

    public abstract ProblemDetail toProblemDetail();
}
