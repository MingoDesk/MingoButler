package com.mingo.butler.controller;

import com.mingo.butler.domain.FileDetails;
import com.mingo.butler.service.FileService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/files")
public class FileControllerImpl extends AbstractFileController {

    public FileControllerImpl(FileService fileService) {
        super(fileService);
    }

    @PostMapping
    protected FileDetails uploadFile(String token, OAuth2Authentication auth, MultipartFile file, HttpServletResponse response) {
        return null;
    }

    @Override
    protected void redirectToImage(String token, OAuth2Authentication auth, HttpServletResponse response) {

    }
}
