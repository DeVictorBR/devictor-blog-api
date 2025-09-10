package tech.devictor.devictor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CategoryNameAlreadyExistException extends DevictorException {

    private final String detail;

    public CategoryNameAlreadyExistException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("This category name already exist");
        pd.setDetail(detail);
        return pd;
    }
}
