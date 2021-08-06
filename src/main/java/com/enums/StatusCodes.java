package com.enums;

public enum StatusCodes {

    SUCCESS(200),
    REDIRECT(300),
    CLIENT_ERROR(400),
    SERVER_ERROR(500);

    private int code;

    public int getCode() {
        return code;
    }

    StatusCodes(int code) {
        this.code = code;
    }
}
