package me.noxiuam.noxlib.command.impl.fun;

import lombok.SneakyThrows;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageToASCII extends GenericCommand {
    public ImageToASCII() {
        super("ascii", "Converts an image to ASCII.", NoxLib.getInstance().prefix + "ascii <attachment>", Tier.GOLD);
    }

    @Override @SneakyThrows
    public void execute(CommandContext ctx) {
        if (ctx.getMessage().getAttachments().size() > 0) {
            try {
                File attachment = ctx.getMessage().getAttachments().get(0).downloadToFile().get();
                BufferedImage bufferedImage = ImageIO.read(attachment);

                System.out.println(NoxLib.getInstance().getAsciiConverter().convert(bufferedImage));
            } catch (Exception e) {
                ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbed("Internal error", e.getMessage(), NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
            }
        } else {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbed("Could not convert image", "You did not provide an image.", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
        }
    }
}
