package tech.devictor.devictor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CategoryWithAssociatedPostsException extends DevictorException {

    private final String detail;

    public CategoryWithAssociatedPostsException(String detail) {
        super(detail);
        this.detail = detail;
    }


    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Category with associated posts");
        pd.setDetail(detail);
        return null;
    }
}
