package ua.com.foxminded.classtimetable.controllers.exceptions;

public class IndelibleEntityException extends RuntimeException {

    private String thrownOutUrl;

    public IndelibleEntityException() {
    }

    public IndelibleEntityException(String message) {
        super(message);
    }

    public IndelibleEntityException(String message, String thrownOutUrl) {
        super(message);
        this.thrownOutUrl = thrownOutUrl;
    }


    public String getThrownOutUrl() {
        return thrownOutUrl;
    }

    public void setThrownOutUrl(String thrownOutUrl) {
        this.thrownOutUrl = thrownOutUrl;
    }
}
