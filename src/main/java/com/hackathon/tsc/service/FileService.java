package com.hackathon.tsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    public String saveFile(MultipartFile file, String fileName) {
        int count = 0;
        int maxTries = 3;
        while(true) {
            try {
                File file1 = convertMultiPartToFile(file, fileName);
                PutObjectResult putObjectResult = amazonS3.putObject("hackathon-bucket-springboard", fileName, file1);
                file1.delete();
                return putObjectResult.getContentMd5();
            } catch (IOException e) {
                if (++count == maxTries) throw new RuntimeException(e);
            }
        }
    }

    public byte[] downloadFile(String filename) {
        S3Object object = amazonS3.getObject("hackathon-bucket-springboard", filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public String deleteFile(String filename) {
        amazonS3.deleteObject("hackathon-bucket-springboard",filename);
        return "File deleted";
    }

    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2("hackathon-bucket-springboard");
        return  listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }


    private File convertMultiPartToFile(MultipartFile file, String fileName) throws IOException {
        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }
}
