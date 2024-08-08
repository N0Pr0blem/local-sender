package com.web.localsender.service;

import com.web.localsender.model.Log;
import com.web.localsender.model.LogStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LogService {
    private ArrayList<Log> logs = new ArrayList<>();

    public LogService(){}

    public ArrayList<Log> getAllCreatedLogs(){
        ArrayList<Log> createdLogs = new ArrayList<>();
        for(Log log : logs){
            if(log.getStatus().equals(LogStatus.CREATED)){
                createdLogs.add(log);
            }
        }
        return createdLogs;
    }

    public void setAllAsDelivered(){
        for(Log log : logs){
            log.setStatus(LogStatus.DELIVERED);
        }
    }

    public void add(String msg){
        logs.add(new Log(msg));
    }

    public ArrayList<Log> getAll() {
        return logs;
    }
}
