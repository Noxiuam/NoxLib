package me.noxiuam.noxlib.media;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class ImageHelper
{

    @SneakyThrows
    public File createRandomImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        File file = new File("C:\\Users\\bsloa\\OneDrive\\Documents\\Noxiuam\\Bots\\3301 Bot\\image\\" + new Random().nextInt() + ".png");
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int pixel = ((int) (Math.random() * 256) << 24)
                        | ((int) (Math.random() * 256) << 16)
                        | ((int) (Math.random() * 256) << 8) | (int) (Math.random() * 256);

                image.setRGB(x, y, pixel);
            }

            ImageIO.write(image, "png", file);
        }

        return file;
    }

}
