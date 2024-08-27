package com.web.localsender.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.web.localsender.config.SftpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class SftpService {

    @Autowired
    private SftpConfig sftpConfig = new SftpConfig();

    @Autowired
    private LogService logService;

    public void uploadFile(MultipartFile file) throws Exception {
        logService.add("To: " + sftpConfig.getHost());
        JSch jsch = new JSch();
        Session session = jsch.getSession(sftpConfig.getUser(), sftpConfig.getHost(), sftpConfig.getPort());
        session.setPassword(sftpConfig.getPassword());

        // Установка свойств сессии
        session.setConfig("StrictHostKeyChecking", "no");
        session.setTimeout(20000);
        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        logService.add("File that will be saved: \n" + file.getOriginalFilename() + "\nFile size: " + file.getSize()+"b");

        try (InputStream inputStream = file.getInputStream()) {
            channelSftp.put(inputStream, sftpConfig.getUploadDir() + "/" + file.getOriginalFilename());
        } finally {
            channelSftp.disconnect();
            session.disconnect();
        }
    }
}
