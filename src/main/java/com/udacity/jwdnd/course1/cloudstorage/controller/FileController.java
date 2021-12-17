package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/add")
    public String addFile(FileForm fileForm, Model model, Authentication authentication) {
        MultipartFile file = fileForm.getFile();
        String fileName = file.getOriginalFilename();
        try {
            if (fileName == null || fileName.equals("")) {
                model.addAttribute("result", "error");
                model.addAttribute("msg", "Need select a file");
            }
            else if(fileService.isDuplicateFileName(fileName, authentication)) {
                model.addAttribute("result", "error");
                model.addAttribute("msg", "The file already existed");
            } else {

                fileService.addFile(file, authentication);
                model.addAttribute("result", "success");

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        List<File> files = fileService.getAllFiles(authentication);
        model.addAttribute("files", files);
        return "result";
    }

    @GetMapping(
            value = "/file/view/{fileId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] viewFile(@PathVariable Integer fileId
                            , @ModelAttribute("fileForm") FileForm fileForm
                            , Model model) {
        return fileService.getFile(fileId).getFileData();
    }

    @GetMapping (
            value = "/file/delete/{fileId}"
    )
    public String deleteFile (@PathVariable Integer fileId
                              , @ModelAttribute("fileForm") FileForm fileForm
                                , Model model, Authentication authentication) {
        fileService.delete(fileId);
        model.addAttribute("result", "success");
        List<File> files = fileService.getAllFiles(authentication);
        model.addAttribute("files", files);
        return "result";
    }

}
