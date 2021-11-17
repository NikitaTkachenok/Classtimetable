package ua.com.foxminded.classtimetable.controllers.exceptions;

import java.util.List;
import java.util.Objects;

public class ValidationErrorResponse {

    public ValidationErrorResponse(List<Violation> violations) {
        this.violations = violations;
    }

    private List<Violation> violations;

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationErrorResponse)) return false;
        ValidationErrorResponse that = (ValidationErrorResponse) o;
        return Objects.equals(violations, that.violations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(violations);
    }

    @Override
    public String toString() {
        return "ValidationErrorResponse{" +
                "violations=" + violations +
                '}';
    }
}
