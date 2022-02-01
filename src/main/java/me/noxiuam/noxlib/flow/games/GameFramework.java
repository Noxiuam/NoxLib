package me.noxiuam.noxlib.flow.games;

import lombok.Getter;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.flow.games.data.GlassBridgeData;
import me.noxiuam.noxlib.flow.games.impl.GlassBridge;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.*;

@Getter
public class GameFramework
{
    public final List<Game> games = new ArrayList<>();
    public final Map<Member, Integer> playerList = new HashMap<>();
    public final Map<Member, Message> runningGames = new HashMap<>();
    public final List<String> gamePieces = new ArrayList<>();

    // Game Stuff
    @Getter public final List<GlassBridgeData> bridgeData = new ArrayList<>();

    public GameFramework()
    {
        this.games.add(new GlassBridge());
        this.gamePieces.add("➡");
        this.gamePieces.add("⬅");
    }

    public void startGame(CommandContext ctx, int id)
    {
        this.getPlayerList().put(ctx.getMember(), id);
        this.getGameById(id).init(ctx, ctx.getMember());
    }

    public Game getGameById(int id)
    {
        return this.games.stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }
}
