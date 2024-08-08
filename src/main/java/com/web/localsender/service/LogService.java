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
            if(log.equals(LogStatus.CREATED)){
                createdLogs.add(log);
                log.setStatus(LogStatus.DELIVERED);
            }
        }
        return createdLogs;
    }

    public void addLog(String msg){
        logs.add(new Log(msg));
    }

    public ArrayList<Log> getAll() {
        return logs;
    }
}
