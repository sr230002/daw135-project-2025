package com.daw135.dawFinalProyect.helpers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.daw135.dawFinalProyect.dto.adjunto.CloudinaryUploadResult;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    private Cloudinary cloudinary;

    @PostConstruct
    public void init() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public CloudinaryUploadResult uploadFile(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "auto" 
        ));

        CloudinaryUploadResult result = new CloudinaryUploadResult();
        result.setSecureUrl(uploadResult.get("secure_url").toString());
        result.setPublicId(uploadResult.get("public_id").toString());
        result.setFormato(uploadResult.get("format").toString());
        result.setTamaño(Long.parseLong(uploadResult.get("bytes").toString()));
        result.setNombreOriginal(file.getOriginalFilename());

        // Determina el tipo de archivo
        String resourceType = uploadResult.get("resource_type").toString();
        result.setTipoArchivo(mapResourceTypeToFileType(resourceType, file.getContentType()));

        return result;
    }

    private String mapResourceTypeToFileType(String resourceType, String contentType) {
        return switch (resourceType) {
            case "image" -> "imagen";
            case "pdf", "raw" -> "documento";
            case "video" -> "video";
            default -> {
                if (contentType != null && contentType.contains("pdf"))
                    yield "pdf";
                yield "otro";
            }
        };
    }

    @SuppressWarnings("rawtypes")
    public void deleteFile(String publicId, String resourceType) throws IOException {
        Map options = ObjectUtils.asMap("resource_type", resourceType);
        cloudinary.uploader().destroy(publicId, options);
    }
}
