package com.my_blog_app.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class ImageService {

    public byte[] multipartFileToByteArray (MultipartFile imageFile) throws IOException {

        byte[] imageByteArray;

        if (!imageFile.isEmpty()) {
            imageByteArray = new byte[imageFile.getBytes().length];
            int i = 0;
            for (byte b : imageFile.getBytes()) {
                imageByteArray[i++] = b;
            }

        } else {
            File templateImageFile = new File("src/main/resources/static/images/template.jpg");
            imageByteArray = Files.readAllBytes(templateImageFile.toPath());
        //  String image = Base64.getEncoder().encodeToString(imageByteArray);

        }

        return imageByteArray;

    }

    public String byteArrayImageToString (byte [] image) {

        String stringImage = Base64.getEncoder().encodeToString(image);

        return stringImage;
    }

}
