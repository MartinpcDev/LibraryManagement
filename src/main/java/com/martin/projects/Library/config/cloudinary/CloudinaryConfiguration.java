package com.martin.projects.Library.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

  @Value("${cloudinary.config.cloud_name}")
  private String cloudName;

  @Value("${cloudinary.config.api_key}")
  private String apiKey;

  @Value("${cloudinary.config.api_secret}")
  private String apiSecret;

  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudName,
        "api_key", apiKey,
        "api_secret", apiSecret
    ));
  }
}
