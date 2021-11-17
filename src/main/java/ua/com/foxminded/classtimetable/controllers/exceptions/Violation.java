package ua.com.foxminded.classtimetable.controllers.exceptions;

import java.util.Objects;

public class Violation {

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    private String fieldName;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Violation)) return false;
        Violation violation = (Violation) o;
        return Objects.equals(fieldName, violation.fieldName) && Objects.equals(message, violation.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, message);
    }

    @Override
    public String toString() {
        return "Violation{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
