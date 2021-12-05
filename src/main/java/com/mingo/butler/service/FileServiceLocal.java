package com.mingo.butler.service;

import com.mingo.butler.domain.FileDetails;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Profile("dev")
@Service
public class FileServiceLocal implements FileService {

    @Override
    public FileDetails uploadFile(String token, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public FileDetails redirectToImage(String token) {
        return null;
    }
}
