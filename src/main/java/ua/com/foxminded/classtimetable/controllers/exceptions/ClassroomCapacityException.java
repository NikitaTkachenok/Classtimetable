package ua.com.foxminded.classtimetable.controllers.exceptions;

public class ClassroomCapacityException extends CommonCustomException {

    public ClassroomCapacityException() {
    }

    public ClassroomCapacityException(String message) {
        super(message);
    }

    public ClassroomCapacityException(String message, String thrownOutUrl) {
        super(message, thrownOutUrl);
    }
}
