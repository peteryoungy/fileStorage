package com.peteryu.filestorage.controller;

import com.peteryu.filestorage.model.File;
import com.peteryu.filestorage.service.FileService;
import com.peteryu.filestorage.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;

import static com.peteryu.filestorage.constant.ConsMsg.*;

@Controller
public class FileController {

    FileService fileService;
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/home/fileupload")
    public String uploadFile(@RequestParam("uploadedFile")MultipartFile multipartFile,
                      Authentication authentication, RedirectAttributes redirectAttributes){

        String fileErr = null;
        int userId = 0;
        String contentType = null;
        String fileName = null;
        long fileSize = 0;

        try{

            userId = userService.getUser(authentication.getName()).getUserId();

            contentType = multipartFile.getContentType();
            fileName = multipartFile.getOriginalFilename();
            fileSize = multipartFile.getSize();

            // note: upload nothing
            if(fileName.length() == 0){
                fileErr = FILE_NOT_SELECTED_ERR;
                throw new Exception("the file cannot be null file");
            }
            // note: >5MB
            // att: what if the file size become bigger?
            if(fileSize > 5242880){
                throw new MaxUploadSizeExceededException(fileSize);

            }
            // note: test if the file (by fileName) is already in DB
            if(fileService.isDuplicate(fileName,userId)){
                fileErr = FILE_DUPLICATE_ERR;
                throw new Exception("you are not allowed to have duplicate file name");
            }

            // note: read data from file to byte[]
            byte[] fileBuffer = null;
            InputStream fis = multipartFile.getInputStream();  // note: get a input stream
            fileBuffer = new byte[fis.available()];
            fis.read(fileBuffer);  // note: read file and store to byte[]
            fis.close();

            File newFile = new File(null, fileName, contentType, String.valueOf(fileSize), userId, fileBuffer);
            if(fileService.addFile(newFile) < 0){
                fileErr = FILE_UPLOAD_FAILED_ERR;
            }

        }
        catch (MaxUploadSizeExceededException e){
            fileErr = FILE_SIZE_LIMIT_EXCEED;
        }
        catch (Exception e){

        }


        // note:
        if(fileErr==null) {
            redirectAttributes.addAttribute("opok",true);
            redirectAttributes.addAttribute("opmsg",FILE_UPLOAD_SUCCESS+": "+fileName);
        } else{
            redirectAttributes.addAttribute("opnotok",true);
            redirectAttributes.addAttribute("opmsg",fileErr+": "+ fileName);
        }

        // note: return to home whenever there's home
        return ("redirect:/home");

    }

    @GetMapping("/home/file/delete/{filename}")
    // note: pathvariable
    public String deleteFile(@PathVariable("filename") String fileName, Authentication authentication, RedirectAttributes redirectAttributes){

        String file_err=null;
        String file_ok=FILE_DELETE_SUCCESS;

        int userId = userService.getUser(authentication.getName()).getUserId();
        try{
            int deletedRow = fileService.deleteFile(userId,fileName);
            if(deletedRow <0 ) file_err=FILE_DELETE_FAILURE;

        }catch(Exception a){
            if(file_err==null) file_err=FILE_UNKNOWN_ERR;
        }


        if(file_err==null) {
            redirectAttributes.addAttribute("opok",true);
            redirectAttributes.addAttribute("opmsg",file_ok+": "+fileName);
        } else{
            redirectAttributes.addAttribute("opnotok",true);
            redirectAttributes.addAttribute("opmsg",file_err+": "+fileName);
        }

        return ("redirect:/home");
    }

    @GetMapping("home/files/download/{filename}")
    public ResponseEntity downloadFile(@PathVariable("filename") String fileName,
                                       Authentication authentication,
                                       RedirectAttributes redirectAttributes,
                                       Model model){

        int userId= userService.getUser(authentication.getName()).getUserId();

        File downloadFile = null;

        try {
            downloadFile = fileService.getFile(userId,fileName);
        }catch (Exception a){
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(downloadFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body(downloadFile.getFileData());

    }
}
