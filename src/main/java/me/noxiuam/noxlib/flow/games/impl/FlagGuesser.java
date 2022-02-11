package me.noxiuam.noxlib.flow.games.impl;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.flow.games.Game;
import me.noxiuam.noxlib.flow.games.data.FlagGuesserData;
import net.dv8tion.jda.api.entities.Member;

public class FlagGuesser extends Game
{

    public FlagGuesser()
    {
        super("Flag Guesser", 2);
    }

    @Override
    public void init(CommandContext ctx, Member member)
    {
        this.ctx = ctx;
        this.run(ctx, member);
    }

    @Override
    public void run(CommandContext ctx, Member member)
    {
        FlagGuesserData game = this.getGame(member.getIdLong());

    }

    @Override
    public void handleGameInput(String unicode, Member member)
    {

    }

    private FlagGuesserData getGame(long memberId)
    {
        return NoxLib.getInstance().getGameFramework().getFlagGuesserData().stream().filter(game -> game.getMemberId() == memberId).findFirst().orElse(null);
    }
}
