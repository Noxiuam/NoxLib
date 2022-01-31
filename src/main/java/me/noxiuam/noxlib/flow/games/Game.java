package me.noxiuam.noxlib.flow.games;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.image.ImageDatabase;
import me.noxiuam.noxlib.util.MessageUtil;
import net.dv8tion.jda.api.entities.Member;

@Getter @AllArgsConstructor
public abstract class Game
{
    private String name;
    private final int id;

    public CommandContext ctx;

    public MessageUtil msg = new MessageUtil();
    public ImageDatabase imageDatabase = new ImageDatabase();

    public Game(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public abstract void init(CommandContext ctx, Member member);

    public abstract void run(CommandContext ctx, Member member);

    public abstract void handleGameInput(String unicode, Member member);

    public void endGame(Member member)
    {
        NoxLib.getInstance().getGameFramework().getBridgeData().remove(NoxLib.getInstance().getGameFramework().getBridgeData().stream().filter(game -> game.getMemberId() == member.getIdLong()).findFirst().orElse(null));
        NoxLib.getInstance().getGameFramework().getRunningGames().remove(member);
    }
}
