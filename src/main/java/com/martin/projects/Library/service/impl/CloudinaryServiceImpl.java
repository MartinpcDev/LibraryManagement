package com.martin.projects.Library.service.impl;

import com.cloudinary.Cloudinary;
import com.martin.projects.Library.service.CloudinaryService;
import jakarta.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

  @Resource
  private Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile file) {
    try {
      HashMap<Object, Object> options = new HashMap<>();
      options.put("folder", "LybraryManagement");
      Map uploadFile = cloudinary.uploader().upload(file.getBytes(), options);
      String publicId = (String) uploadFile.get("public_id");
      return cloudinary.url().secure(true).generate(publicId);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
