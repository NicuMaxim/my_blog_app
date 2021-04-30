package com.my_blog_app.service;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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


    public byte[] createThumbnail (MultipartFile originalImage, Integer width) throws IOException {

        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage img = ImageIO.read(originalImage.getInputStream());
        BufferedImage thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbImg, originalImage.getContentType().split("/") [1], thumbOutput);
        byte[] byteArrayOutput = thumbOutput.toByteArray();
        return byteArrayOutput;
    }


}
