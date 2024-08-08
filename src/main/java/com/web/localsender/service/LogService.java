package com.web.localsender.service;

import com.web.localsender.model.Log;
import com.web.localsender.model.LogStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogService {
    private Map<Log, LogStatus> map = new HashMap<>();
}
