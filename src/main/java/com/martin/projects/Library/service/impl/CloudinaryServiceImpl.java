package com.martin.projects.Library.service.impl;

import com.cloudinary.Cloudinary;
import com.martin.projects.Library.service.CloudinaryService;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {


  private final Cloudinary cloudinary;

  private final static List<String> ALLOWED_EXTENSION_FILE = Arrays.asList("png", "jpg", "jpeg");

  private final String FOLDER_NAME = "LybraryManagement";

  @Autowired
  public CloudinaryServiceImpl(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }

  @Override
  public String uploadFile(MultipartFile image) {
    try {
      String originalFileName = image.getOriginalFilename();

      if (originalFileName == null || !isExtensionAllowed(originalFileName)) {
        throw new MultipartException(
            "Extension de archivo invalida solo se permiten : "
                + ALLOWED_EXTENSION_FILE);
      }

      HashMap<Object, Object> options = new HashMap<>();
      options.put("folder", FOLDER_NAME);
      var uploadFile = cloudinary.uploader().upload(image.getBytes(), options);
      String publicId = (String) uploadFile.get("public_id");
      return cloudinary.url().secure(true).generate(publicId);
    } catch (IOException exception) {
      throw new MultipartException("Error al subir el archivo de imagen", exception);
    }
  }

  @Override
  public boolean isExtensionAllowed(String originalFileName) {
    String[] fileExtension = originalFileName.split("\\.");
    return ALLOWED_EXTENSION_FILE.contains(fileExtension[1]);
  }
}
