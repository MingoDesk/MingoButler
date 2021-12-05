package com.mingo.butler.controller;

import com.mingo.butler.domain.FileDetails;
import com.mingo.butler.service.FileService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractFileController {

    private final FileService fileService;

    public AbstractFileController(FileService fileService) {
        this.fileService = fileService;
    }

    protected abstract FileDetails uploadFile(@RequestHeader(name="Authorization") String token, OAuth2Authentication auth,
                                              MultipartFile file, HttpServletResponse response);

    protected abstract void redirectToImage(@RequestHeader(name="Authorization") String token, OAuth2Authentication auth,
                                      HttpServletResponse response);
}
