package me.noxiuam.noxlib.command.impl.fun;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.*;
import me.noxiuam.noxlib.command.util.CommandContext;

public class RandomImage extends GenericCommand
{

    public RandomImage()
    {
        super("randomimage", "Creates a randomly generated image.", NoxLib.getInstance().getPrefix() + "randomimage");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        ctx.getMessage().reply(NoxLib.getInstance().getImageDatabase().getHelper().createRandomImage(256, 256), "image.png").queue();
    }
}
