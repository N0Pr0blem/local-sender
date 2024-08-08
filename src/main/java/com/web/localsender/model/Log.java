package com.web.localsender.model;

import java.util.Date;

public class Log {

    String message;
    Date date;

    public Log(String message, Date date) {
        this.message = message;
        this.date = date;
    }
    @Override
    public String toString() {
        return date + ": " + message;
    }
}
