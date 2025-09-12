package tech.devictor.devictor.exceptions;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TagWithAssociatedPostsException extends DevictorException {

    private final String detail;

    public TagWithAssociatedPostsException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Tag with associated posts");
        pd.setDetail(detail);
        return pd;
    }
}
