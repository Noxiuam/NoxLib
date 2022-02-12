package me.noxiuam.noxlib.feature.games.impl;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.feature.games.Game;
import me.noxiuam.noxlib.util.data.user.GenericGameProfile;
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
        GenericGameProfile game = this.getGame(member.getIdLong());

    }

    @Override
    public void handleGameInput(String unicode, Member member)
    {

    }

    private GenericGameProfile getGame(long memberId)
    {
        return NoxLib.getInstance().getGameFramework().getGameProfiles().stream().filter(game -> game.getMemberId() == memberId && game.getGameId() == 2).findFirst().orElse(null);
    }
}
