package com.howhow.course.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.howhow.course.exception.NoCourseException;
import com.howhow.entity.CourseBasic;

@Configuration
@RestController
@RequestMapping("blob")
public class BlobController {
	
	
	
	
	@Value("azure-blob://mycontainer/stoageno1")
    private Resource blobFile;
	
	@Value("${AZURE.STORAGE.CONNECTION.STRING}")
	private String connectStr;

	@Bean
	public BlobContainerClient blobContanerClient() {
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
    	String containerName = "mycontainer" ;

    	// Create the container and return a container client object
    	BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
    	
		return containerClient;
	}
	
    @GetMapping("/readBlobFile")
    public String readBlobFile() throws IOException {
        return StreamUtils.copyToString(
                this.blobFile.getInputStream(),
                Charset.defaultCharset());
    }

    @PostMapping("/writeBlobFile")
    public String writeBlobFile() throws IOException {
    	System.out.println(connectStr);
    	// Create a BlobServiceClient object which will be used to create a container client
    	BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
    	InputStream inp= new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
    	//Create a unique name for the container
    	String containerName = "mycontainer" ;

    	// Create the container and return a container client object
    	BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
    	System.out.println("getcontainerClient");
    	BlobClient blobClient = containerClient.getBlobClient("travel.mp4");
    	System.out.println("blobClient is ok");
    	
    	blobClient.uploadFromFile("src/main/resources/static/video/travel.mp4");
    	blobClient.upload(null);
        return "file was updated";
    }
    
	@PostMapping(value= "/api/testForign")
	public ResponseEntity testForign(@RequestParam("file") MultipartFile multipartfile,
			@RequestParam("videofile") MultipartFile videofile,
			@RequestParam("courseName") String courseName,
			@RequestParam("category") String category,
			@RequestParam("price") String price,
			@RequestParam("createtime") String createtime
			) throws IOException, NoCourseException {
		
		////////////////video/////////////////
		CourseBasic course= new CourseBasic();
		if (!multipartfile.isEmpty()) {
			String fileName=StringUtils.cleanPath(multipartfile.getOriginalFilename());
			course.setCourseCover(fileName);
			CourseBasic saveCourse= null;
//			if(service.editCourse(course)) {
//				 saveCourse =service.findCourseByUIDAndName(course.getCreator().getUid(), course.getCourseName());
//			}
			
			
			String uploadDir = "course-photos/" +saveCourse.getCourseId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
			///////////////////////////////////////////////
//			Path ImageDir=Paths.get("../course-photos");
//			String ImageDirPath =ImageDir.toFile().getAbsolutePath();
//			
//			String src="file:\\"+ ImageDirPath +"\\"+fileName;
			
			String src="../course-photos/" +saveCourse.getCourseId() +"/"+fileName;
			
			
//			String images="/howhow/images"+"/"+fileName;
			/////////////////////////////////////////////
		
//			    System.out.println("ImageDirPath="+ImageDirPath);
			System.out.println(src);
			
			
			//////////////////////////////////////////////////////////////////////////
			if (!videofile.isEmpty()) {
				String videofileName=StringUtils.cleanPath(videofile.getOriginalFilename());
				String videouploadDir = "course-videos/"  +saveCourse.getCourseId() +"/"+videofileName;
				
				FileUploadUtil.cleanDir(videouploadDir);
				FileUploadUtil.saveFile(videouploadDir, videofileName, videofile);
				
			}
			
			
			
			//////////////////////////////////////////////////////////////////////////
			 HttpHeaders headers = new HttpHeaders();
			    headers.add("Access-Control-Allow-Origin", "*");
			    headers.add("Content-Type","application/json");
			
			return  ResponseEntity.ok().headers(headers).body(src);
		}else {
//			if(user.getPhotos().isEmpty() ) user.setPhotos(null);
			course.setCourseCover(null);
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Access-Control-Allow-Origin", "*");
		    headers.add("Content-Type","application/json");
		
//			service.saveCourse(course);
			return ResponseEntity.ok().headers(headers).body("ok");
		}
		
		
		
	}
}
