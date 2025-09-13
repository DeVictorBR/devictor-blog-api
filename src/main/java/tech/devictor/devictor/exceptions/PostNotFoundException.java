package tech.devictor.devictor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PostNotFoundException extends DevictorException {

    private final String detail;

    public PostNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Post not found");
        pd.setDetail(detail);
        return pd;
    }
}
