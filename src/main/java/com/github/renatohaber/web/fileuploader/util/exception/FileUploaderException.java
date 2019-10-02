package com.github.renatohaber.web.fileuploader.util.exception;
import com.github.renatohaber.web.fileuploader.enums.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUploaderException extends Exception {

    private HttpStatus httpStatus;
    private List<Error> errors;

    public FileUploaderException(String message) {
        super(message);
    }

    public FileUploaderException(String message, Exception exception) {
        super(message, exception);
    }

    public FileUploaderException(HttpStatus httpStatus, Exception exception) {
        super(String.format("HTTP %1$s - %2$s [%3$s] \n %4$s", httpStatus.getCode(), httpStatus,
                httpStatus.getDescription(), exception.getMessage()));
        this.httpStatus = httpStatus;
    }

    public FileUploaderException(HttpStatus httpStatus) {
        super(String.format("HTTP %1$s - %2$s [%3$s]", httpStatus.getCode(), httpStatus, httpStatus.getDescription()));
        this.httpStatus = httpStatus;
    }

    public FileUploaderException(HttpStatus httpStatus, List<Error> erros) {
        super(String.format("HTTP %1$s - %2$s [%3$s]", httpStatus.getCode(), httpStatus, httpStatus.getDescription()));
        this.httpStatus = httpStatus;
        this.errors = erros;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<Error> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<Error>();
        }
        return this.errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        if (!this.getErrors().isEmpty()) {
            for (Iterator<Error> iter = this.errors.iterator(); iter.hasNext(); ) {
                Error error = iter.next();
                message.append("\n").append(error.getCode()).append(" - ").append(error.getMessage());
            }
        }

        return message.toString();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}

class Error {

    private String code;
    private String message;

    public Error() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

