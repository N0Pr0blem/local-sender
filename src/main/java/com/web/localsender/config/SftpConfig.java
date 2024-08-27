package com.web.localsender.config;

import com.web.localsender.component.IpConfigurator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class SftpConfig {
    private  String host = IpConfigurator.getIp();
    @Value("${sftp.port}")
    private int port;
    @Value("${sftp.user}")
    private String user;
    @Value("${sftp.password}")
    private String password;
    @Value("${sftp.upload.dir}")
    private String uploadDir;

}
