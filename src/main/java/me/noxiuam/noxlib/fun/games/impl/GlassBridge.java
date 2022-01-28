package me.noxiuam.noxlib.fun.games.impl;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.fun.games.Game;
import net.dv8tion.jda.api.entities.Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GlassBridge extends Game
{

    private Message message;

    private int stagesLeft;
    private boolean willBreak;
    private long memberId;

    private final String glassEmote = ":ice_cube:";
    private String gameBoard = glassEmote + glassEmote;

    public GlassBridge()
    {
        super("Glass Bridge [Squid Game Series]", 1, false);
    }

    @Override
    public void init(CommandContext ctx)
    {
        this.ctx = ctx;
        this.memberId = ctx.getMember().getIdLong();
        if (this.gameBoard.contains(":x:"))
        {
            this.gameBoard = glassEmote + glassEmote;
        }
        this.stagesLeft = 10;
        this.willBreak = new Random().nextBoolean();
        this.run(ctx);
    }

    @Override
    public void run(CommandContext ctx)
    {
        ctx.getMessage().reply(msg.createEmbedWithAuthor(this.getName(), "You have two pieces of glass in front of you.\n\nYou must jump through the rest as quickly as possible.\n\n" + this.gameBoard, "Levels Left: " + this.stagesLeft).build()).queue(m ->
        {
            m.addReaction("\u2B05").queue();
            m.addReaction("\u27A1").queue();
            this.message = m;
        });
    }

    public void handleGameInput(String choice)
    {
        boolean movingRight = choice.equalsIgnoreCase("➡");
        this.willBreak = new Random().nextBoolean();
        this.stagesLeft--;

        if (willBreak)
        {
            this.gameBoard = (movingRight ? this.glassEmote + " " + ":x:" : ":x:" + " " + this.glassEmote);
            this.message.editMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor(this.getName(), "You have jumped to the tile to the " + (movingRight ? "right" : "left") + ", but it broke and you fell to your death.\n\n" + this.gameBoard, "GAME OVER (Levels Left: " + this.stagesLeft + ")").build()).queue(m -> {
                this.ctx.getMessage().delete().queueAfter(10, TimeUnit.SECONDS);
                m.delete().queueAfter(10, TimeUnit.SECONDS);
            });
            this.endGame(this.memberId);
        }
        else
        {
            if (this.stagesLeft == 0)
            {
                this.message.removeReaction("\u2B05").queue();
                this.message.removeReaction("\u27A1").queue();

                this.message.editMessage(NoxLib.getInstance().getMessageUtil().createEmbed(this.getName(), "You have made it to the end!\n\nThank you for playing.").build()).queue(m -> m.delete().queueAfter(10, TimeUnit.SECONDS));
                this.endGame(this.memberId);
                return;
            }

            this.gameBoard = this.glassEmote + " " + this.glassEmote;
            this.message.editMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor("Please wait for the next level...", "You have jumped to the tile to the " + (movingRight ? "right" : "left") + ", and luckily, you lived.\n\n" + this.gameBoard, "Levels Left: " + this.stagesLeft).build()).queue(m -> m.editMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithAuthor(this.getName(), "You have two pieces of glass in front of you.\n\nYou must jump through the rest as quickly as possible.\n\n" + this.gameBoard, "Levels Left: " + this.stagesLeft).build()).queueAfter(3, TimeUnit.SECONDS));
        }
    }
}
