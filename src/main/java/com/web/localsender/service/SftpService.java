package com.web.localsender.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class SftpService {

    private static final String SFTP_HOST = "192.168.0.101"; // адрес вашего SFTP-сервера
    private static final int SFTP_PORT = 22; // порт SFTP
    private static final String SFTP_USER = "n0pr0blem"; // имя пользователя
    private static final String SFTP_PASSWORD = "6021"; // пароль
    private static final String SFTP_UPLOAD_DIR = "/home/n0pr0blem/uploads"; // директория для загрузки

    private Logger logger = LogManager.getLogger(SftpService.class);

    public void uploadFile(MultipartFile file) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASSWORD);

        // Установка свойств сессии
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        logger.info("File that will be saved: " + file.getOriginalFilename());
        logger.warn("Is file empty? - "+file.isEmpty());

        try (InputStream inputStream = file.getInputStream()) {
            channelSftp.put(inputStream, SFTP_UPLOAD_DIR + "/" + file.getOriginalFilename());
        } finally {
            channelSftp.disconnect();
            session.disconnect();
        }
    }
}
