package me.noxiuam.noxlib.command.normal.fun.game;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

import java.util.concurrent.TimeUnit;

public class Game extends Command
{
    public Game()
    {
        super("game", "Plays a certain game, based off its ID.", NoxLib.getInstance().getPrefix() + "game <game id>");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        if (ctx.getArgs().isEmpty())
        {
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Starting Game", "You did not specify a game command to run!\nHelpful Commands:`list, start, stop`", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("list"))
        {
            StringBuilder sb = new StringBuilder();
            for (me.noxiuam.noxlib.flow.games.Game game : NoxLib.getInstance().getGameFramework().getGames())
            {
                sb.append("**").append(game.getId()).append(". ").append(game.getName()).append("**\n");
            }
            ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("__Currently Available Games__", sb + "\n\n*To play a game, use* `" + NoxLib.getInstance().getPrefix() +"game start <game id>`", NoxLib.getInstance().getImageDatabase().getGameImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("stop"))
        {

            if (!NoxLib.getInstance().getGameFramework().getRunningGames().containsKey(ctx.getMember()))
            {
                ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Stopping Game", "You do not have a game running currently!\n\nTo start a game, type `" + NoxLib.getInstance().getPrefix() + "game start <game id>`", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
                return;
            }

            NoxLib.getInstance().getGameFramework().getRunningGames().remove(ctx.getMember());
            ctx.getChannel().sendMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Game Ended", "**You've ended your game.**", NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
            return;
        }

        if (ctx.getArgs().get(0).equalsIgnoreCase("start"))
        {
            if (NoxLib.getInstance().getGameFramework().getRunningGames().containsKey(ctx.getMember()))
            {
                ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Starting Game", "You already have a game running!\n\nTo end your current game, type `" + NoxLib.getInstance().getPrefix() + "game stop`", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue();
                return;
            }
            try
            {
                NoxLib.getInstance().getGameFramework().startGame(ctx, Integer.parseInt(ctx.getArgs().get(1)));
            }
            catch (NumberFormatException e)
            {
                ctx.getMessage().replyEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error Starting Game", "Could not find that game, please try again!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
            }
        }
    }
}
