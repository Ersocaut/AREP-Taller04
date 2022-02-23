package edu.escuelaing.arep.services;

import edu.escuelaing.arep.annotation.Component;
import edu.escuelaing.arep.annotation.RequestMapping;
import server.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static edu.escuelaing.arep.util.Constants.RESOURCES_PATH;
import static server.HttpServer.*;

@Component
public class ImgService {
    @RequestMapping("img")
    public static String computeImage() throws URISyntaxException {
        String responseContent;
        String extensionUri = "png";
        URI resourceURI = new URI("/public/img.png");

        responseContent = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + types.get(extensionUri) + "\r\n"
                + "\r\n";

        File file = new File(RESOURCES_PATH + resourceURI.getPath());
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(HttpServer.outputStream);
            ImageIO.write(bi, extensionUri, byteArrayOutputStream);
            dataOutputStream.writeBytes(responseContent);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }
}
