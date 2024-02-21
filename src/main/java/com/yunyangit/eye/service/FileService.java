package com.yunyangit.eye.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yunyangit.eye.model.FileInfo;

public interface FileService {
	
	FileInfo save(MultipartFile file, Long type, Long fileId, Long type1) throws IOException;
	
	void get(String fileName, final HttpServletResponse response) throws Exception;
}
