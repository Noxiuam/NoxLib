package me.noxiuam.noxlib.feature.games.impl;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.util.data.user.GlassBridgeData;
import me.noxiuam.noxlib.feature.games.Game;
import net.dv8tion.jda.api.entities.Member;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GlassBridge extends Game
{
    public GlassBridge()
    {
        super("Glass Bridge [Squid Game Series]", 1, "\u2B05", "\u27A1");
    }

    @Override
    public void init(CommandContext ctx, Member member)
    {
        GlassBridgeData gameInit = new GlassBridgeData(member.getIdLong(), 10, false, ":ice_cube:" + ":ice_cube:");
        NoxLib.getInstance().getGameFramework().getBridgeData().add(gameInit);
        this.ctx = ctx;
        this.run(ctx, member);
    }

    @Override
    public void run(CommandContext ctx, Member member)
    {
        GlassBridgeData game = this.getGame(member.getIdLong());

        ctx.getMessage().replyEmbeds(msg.createEmbedWithAuthor(
                this.getName(),
                "You have two pieces of glass in front of you.\n\nYou must jump through the rest as quickly as possible.\n\n" + game.getGameBoard(),
                "Levels Left: " + game.getStagesLeft()).build()).queue(m ->
        {
            m.addReaction("\u2B05").queue();
            m.addReaction("\u27A1").queue();
            NoxLib.getInstance().getGameFramework().getRunningGames().put(member, m);
        });
    }

    public void handleGameInput(String choice, Member member)
    {
        GlassBridgeData game = this.getGame(member.getIdLong());
        boolean movingRight = choice.equalsIgnoreCase("➡");

        game.setWillBreak(new Random().nextBoolean());
        game.setStagesLeft(game.getStagesLeft() - 1);

        if (Objects.requireNonNull(game).isWillBreak())
        {
            game.setGameBoard((movingRight ? ":ice_cube: " + ":x:" : ":x:" + " :ice_cube:"));
            NoxLib.getInstance().getGameFramework().getRunningGames().get(member).editMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor(this.getName(), "You have jumped to the tile to the " + (movingRight ? "right" : "left") + ", but it broke and you fell to your death.\n\n" + game.getGameBoard(), "GAME OVER (Levels Left: " + game.getStagesLeft() + ")").build()).queue();
            this.endGame(member);
        }
        else
        {
            if (Objects.requireNonNull(game).getStagesLeft() == 0)
            {
                NoxLib.getInstance().getGameFramework().getRunningGames().get(member).removeReaction("\u2B05").queue();
                NoxLib.getInstance().getGameFramework().getRunningGames().get(member).removeReaction("\u27A1").queue();

                NoxLib.getInstance().getGameFramework().getRunningGames().get(member).editMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbed(this.getName(), "You have made it to the end!\n\nThank you for playing.").build()).queue();
                this.endGame(member);
                return;
            }

            game.setGameBoard(":ice_cube: :ice_cube:");
            NoxLib.getInstance().getGameFramework().getRunningGames().get(member).editMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor("Please wait for the next level...", "You have jumped to the tile to the " + (movingRight ? "right" : "left") + ", and luckily, you lived.\n\n" + game.getGameBoard(), "Levels Left: " + game.getStagesLeft()).build()).queue(m -> m.editMessageEmbeds(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor(this.getName(), "You have two pieces of glass in front of you.\n\nYou must jump through the rest as quickly as possible.\n\n" + game.getGameBoard(), "Levels Left: " + game.getStagesLeft()).build()).queueAfter(3, TimeUnit.SECONDS));
        }
    }

    private GlassBridgeData getGame(long memberId)
    {
        return NoxLib.getInstance().getGameFramework().getBridgeData().stream().filter(game -> game.getMemberId() == memberId).findFirst().orElse(null);
    }
}
