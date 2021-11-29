package ua.com.foxminded.classtimetable.controllers.exceptions;

public class InvalidLessonConditionsException extends CommonCustomException {

    public InvalidLessonConditionsException() {
    }

    public InvalidLessonConditionsException(String message) {
        super(message);
    }

    public InvalidLessonConditionsException(String message, String thrownOutUrl) {
        super(message, thrownOutUrl);
    }
}
