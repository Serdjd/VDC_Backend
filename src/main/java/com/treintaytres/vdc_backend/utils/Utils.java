package com.treintaytres.vdc_backend.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static String saveImage(byte[] profileImageData, String email) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(profileImageData);
        BufferedImage profileImage = ImageIO.read(inputStream);

        BufferedImage scaleImage = new BufferedImage(200,200, profileImage.getType());
        Graphics2D g = scaleImage.createGraphics();
        g.drawImage(profileImage, 0, 0,200,200, null);
        g.dispose();

        File directory = new File("/images/users/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File outputFile = new File("/images/users/"+email+".png");
        ImageIO.write(scaleImage, "png", outputFile);

        return email+".png?hash="+System.currentTimeMillis();
    }

    public static String saveImage(byte[] image, String name, String type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
        BufferedImage profileImage = ImageIO.read(inputStream);

        BufferedImage scaleImage = new BufferedImage(200,200, profileImage.getType());
        Graphics2D g = scaleImage.createGraphics();
        g.drawImage(profileImage, 0, 0,200,200, null);
        g.dispose();

        File directory = new File("/images/"+type+"/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        name = name.replaceAll(" ", "_");

        File outputFile = new File("/images/"+type+"/"+name+".png");
        ImageIO.write(scaleImage, "png", outputFile);

        System.out.println(outputFile.exists());

        return name+".png?hash="+System.currentTimeMillis();
    }

}
