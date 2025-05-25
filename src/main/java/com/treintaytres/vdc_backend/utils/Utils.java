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

        File outputFile = new File("/images/users/"+email+".jpg");
        ImageIO.write(scaleImage, "jpg", outputFile);

        return email+".jpg";
    }
}
