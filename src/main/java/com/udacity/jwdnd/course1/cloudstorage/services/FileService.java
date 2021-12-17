package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public void addFile(MultipartFile file, Authentication authentication) throws IOException {
        InputStream is = file.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            baos.write(data, 0, nRead);
        }
        baos.flush();
        byte[] fileData = baos.toByteArray();

        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String fileSize = String.valueOf(file.getSize());
        Integer userId = userMapper.getUser(authentication.getName()).getUserId();
        File f = new File();
        f.setFileData(fileData);
        f.setFileName(fileName);
        f.setContentType(contentType);
        f.setFileSize(fileSize);
        f.setUserId(userId);

        fileMapper.addFile(f);
    }

    public File getFile(int fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getAllFiles(Authentication authentication) {
        int userId = userMapper.getUser(authentication.getName()).getUserId();
        return fileMapper.getAllFiles(userId);
    }

    public void delete(int fileId) {
        fileMapper.deleteFile(fileId);
    }

    public boolean isDuplicateFileName(String name, Authentication authentication) {
        for (File f : getAllFiles(authentication)) {
            if(f.getFileName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
