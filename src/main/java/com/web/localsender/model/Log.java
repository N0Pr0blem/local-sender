package com.web.localsender.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Log {
    @Setter
    @Getter
    private LogStatus status;
    private String message;
    private Date date;

    public Log(String message) {
        this.message = message;
        this.date = new Date();
        status = LogStatus.CREATED;
    }

    @Override
    public String toString() {
        return date + ":\n " + message;
    }
}
