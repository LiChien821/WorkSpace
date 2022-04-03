package com.howhow.course.api;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

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

}
