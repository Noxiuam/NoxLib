package me.noxiuam.noxlib.fun.games;

import lombok.*;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.image.ImageDatabase;
import me.noxiuam.noxlib.util.MessageUtil;

@Getter @AllArgsConstructor
public abstract class Game
{
    private String name;
    private boolean multiplayer;
    private boolean waiting;
    private final int id;

    public CommandContext ctx;

    public MessageUtil msg = new MessageUtil();
    public ImageDatabase imageDatabase = new ImageDatabase();

    public Game(String name, int id, boolean multiplayer)
    {
        this.name = name;
        this.id = id;
        this.multiplayer = multiplayer;
    }

    public abstract void init(CommandContext ctx);

    public abstract void run(CommandContext ctx);

    public abstract void handleGameInput(String unicode);

    public void endGame(long memberId)
    {
        NoxLib.getInstance().getGameFramework().getRunningGames().remove(memberId);
    }

}
