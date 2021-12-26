package com.example.email_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
@RestController
@RequestMapping("/file")
public class FileController {
    //set a location to store uploaded files
    public static final String DIRECTORY = System.getProperty("user.home")+"/Desktop/email-server/back/";

    //upload files method
    @PostMapping("/upload")
    //names of files uploaded
    public ResponseEntity<List<String>> uploadFile(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<>(); //save file names and return them to the user
        for(MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            //Path fileStorage = get(fileName).toAbsolutePath().normalize();
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            System.out.println("UPLOAD STORAGE"+fileStorage);
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            fileNames.add(fileName);
        }
        return ResponseEntity.ok().body(fileNames);
    }
    //download files method
    @GetMapping("download/{filename}") //path
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName); //entire path to the loc fo the file including the file name
        System.out.println("DOWN PATH"+filePath);
        if(!Files.exists(filePath)){
            throw new FileNotFoundException(fileName+"File not found.");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
    }
}
