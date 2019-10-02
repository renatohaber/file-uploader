package com.github.renatohaber.web.fileuploader.ws.service;

import com.github.renatohaber.web.fileuploader.property.FileStorageProperties;
import com.github.renatohaber.web.fileuploader.util.exception.FileUploaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws FileUploaderException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileUploaderException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    public String storeFile(MultipartFile file) throws FileUploaderException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileUploaderException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileUploaderException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public Resource loadFileAsResource(String fileName) throws FileUploaderException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileUploaderException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileUploaderException("File not found " + fileName);
        }
    }
}
