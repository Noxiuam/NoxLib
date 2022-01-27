package me.noxiuam.noxlib.image;

import lombok.*;
import net.dv8tion.jda.api.entities.MessageChannel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

@Setter @Getter
public class ImageDatabase {
    public String defaultImage = "https://i.imgur.com/3CWMDif.gif"/*https://officialpsds.com/imageview/7v/90/7v90vz_large.png"*/;
    public String toolImage = "https://www.freeiconspng.com/uploads/tool-icon-12.png";
    public String errorImage = "https://www.pinclipart.com/picdir/big/548-5481377_danger-warning-sign-png-clipart-high-voltage-sign.png";

    @SneakyThrows
    public void createRandomImage(int width, int height, MessageChannel channel) {
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

        channel.sendFile(file, "image.png").queue();
        if (file.delete())
        {
            System.out.println("[NoxLib] Deleted image");
        }
        else
        {
            System.out.println("[NoxLib] Could not delete image");
        }
    }
}
