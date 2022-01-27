package me.noxiuam.noxlib.command.fun;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.*;

public class RandomImage extends Command
{

    public RandomImage()
    {
        super("randomimage", "Creates a randomly generated image.", NoxLib.getInstance().getPrefix() + "randomimage");
    }

    @Override
    public void execute(CommandContext ctx) {

        NoxLib.getInstance().getImageDatabase().createRandomImage(256, 256, ctx.getChannel());

    }
}
