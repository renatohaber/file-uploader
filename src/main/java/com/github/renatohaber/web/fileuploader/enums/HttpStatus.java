package com.github.renatohaber.web.fileuploader.enums;

public enum HttpStatus {

    OK(200, "STANDARD RESPONSE FOR SUCCESSFUL HTTP REQUESTS"),
    BAD_REQUEST(400, "THE REQUEST CANNOT BE FULFILLED DUE TO BAD SYNTAX"),
    UNAUTHORIZED(401, "THE AUTHENTICATION IS POSSIBLE BUT HAS FAILED"),
    FORBIDDEN(403, "THE SERVER REFUSES TO RESPOND TO REQUEST"),
    NOT_FOUND(404, "THE REQUESTED RESOURCE COULD NOT BE FOUND"),
    INTERNAL_SERVER_ERROR(500, "GENERIC ERROR MESSAGE"),
    BAD_GATEWAY(502, "SERVER RECEIVED AN INVALID RESPONDE FROM UPSTREAM SERVER"),
    SERVICE_UNAVAILABLE(503, "THE SERVER IS CURRENTLY UNAVAILABLE"),
    UNKNOWN_STATUS(-1, "UNKNOWN STATUS. SEE ONLINE DOCUMENTATION");

    private Integer code;
    private String description;

    HttpStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static HttpStatus fromCode(Integer code) {
        for (HttpStatus httpStatus : values()) {
            if (httpStatus.code.equals(code)) {
                return httpStatus;
            }
        }
        UNKNOWN_STATUS.setCode(code);
        return UNKNOWN_STATUS;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
