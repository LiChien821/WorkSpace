package com.howhow.course.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	private static final Logger LOGGER =LoggerFactory.getLogger(FileUploadUtil.class);
	public static void saveFile(String uploadDir,
			String fileName,MultipartFile multipartFile) throws IOException {
		Path uploadPath =Paths.get(uploadDir);
	  System.out.println("This do uploadPath =Paths.get(uploadDir) ="+uploadPath);
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()){
			Path filePath =uploadPath.resolve(fileName);
			  System.out.println("This do Path filePath =uploadPath.resolve(fileName); ="+filePath);
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);	
		}catch(IOException ex) {
		throw new IOException("couldn't save file",ex);
		}
	}
	
	
	public static void cleanDir(String dir) {
		Path dirPath =Paths.get(dir);
		
		try {
			Files.list(dirPath).forEach(file ->{
				if(!Files.isDirectory(file)) {
					try {
						Files.delete(file);
					}catch(IOException ex) {
						LOGGER.error("could not delete file");

						System.out.println("could not delete file");
					}
				}
			});
		}catch(IOException ex) {
			LOGGER.error("could not list directory"+dirPath);
			System.out.println("could not list directory"+dirPath);
		}
	}
}