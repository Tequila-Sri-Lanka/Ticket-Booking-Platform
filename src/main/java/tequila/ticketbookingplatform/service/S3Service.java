package tequila.ticketbookingplatform.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${aws.region}")
    private String region;

    public S3Service(
            @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
        this.s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();
    }

    public String uploadFile(File file, String folder) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "_" + file.getName();
        String fileKey = folder + "/" + uniqueFileName;

        try (FileInputStream inputStream = new FileInputStream(file)) {
            String contentType = Files.probeContentType(file.toPath()); // Get file MIME type

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileKey)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.length()));
        }

        return uniqueFileName;
    }

    public String getFileUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }

    public boolean deleteFile(String fileName, String folder) {
        String fileKey = folder + "/" + fileName;

        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileKey)
                    .build();

            s3Client.headObject(headObjectRequest); // Check if the file exists

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileKey)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
            return true;
        } catch (NoSuchKeyException e) {
            System.err.println("File not found: " + e.getMessage());
            return false;
        } catch (SdkException e) {
            System.err.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }

    public String updateFile(File newFile, String existingFileName, String folder) throws IOException {
        boolean isDeleted = deleteFile(existingFileName, folder);
        if (isDeleted) {
            return uploadFile(newFile, folder);
        } else {
            throw new IOException("Failed to delete the existing file before updating.");
        }
    }
}
