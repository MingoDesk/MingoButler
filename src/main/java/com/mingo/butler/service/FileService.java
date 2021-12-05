package com.mingo.butler.service;

import com.mingo.butler.domain.FileDetails;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileDetails uploadFile(String token, MultipartFile multipartFile);

    FileDetails redirectToImage(String token);
}
