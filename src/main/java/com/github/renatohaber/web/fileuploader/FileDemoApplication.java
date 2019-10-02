package com.github.renatohaber.web.fileuploader;

import com.github.renatohaber.web.fileuploader.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FileDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileDemoApplication.class, args);
    }
}
